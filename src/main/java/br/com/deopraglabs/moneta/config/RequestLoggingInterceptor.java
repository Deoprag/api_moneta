package br.com.deopraglabs.moneta.config;

import br.com.deopraglabs.moneta.domain.RequestLog;
import br.com.deopraglabs.moneta.domain.User;
import br.com.deopraglabs.moneta.repositories.RequestLogRepository;
import io.micrometer.common.lang.NonNullApi;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.CompletableFuture;

@Component
@NonNullApi
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private final RequestLogRepository logRepository;

    public RequestLoggingInterceptor(RequestLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, @Nullable Exception ex
    ) {
        final long duration = System.currentTimeMillis() - (long) request.getAttribute("startTime");
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = null;
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails) {
            user = (User) auth.getPrincipal();
        }

        final RequestLog log = RequestLog.builder()
                .method(request.getMethod())
                .endpoint(request.getRequestURI())
                .statusCode((short) response.getStatus())
                .ipAddress(request.getRemoteAddr())
                .userAgent(request.getHeader("User-Agent"))
                .user(user)
                .durationMs(duration)
                .build();

        CompletableFuture.runAsync(() -> logRepository.save(log));
    }
}
