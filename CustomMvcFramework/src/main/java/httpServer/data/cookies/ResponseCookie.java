package httpServer.data.cookies;

import httpServer.data.enumerations.SameSiteType;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ResponseCookie extends Cookie {
    private String domain;
    private String path;
    private OffsetDateTime expires;
    private int maxAge;
    private boolean isSecure;
    private boolean isHttpOnly;
    private SameSiteType sameSite;

    public ResponseCookie(String name, String value) {
        super(name, value);
        this.path = "/";
        this.expires = OffsetDateTime.now(ZoneOffset.UTC).plus(Duration.ofHours(1));
        this.sameSite = SameSiteType.NONE;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public OffsetDateTime getExpires() {
        return expires;
    }

    public void setExpires(OffsetDateTime expires) {
        this.expires = expires;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public boolean isSecure() {
        return isSecure;
    }

    public void setSecure(boolean secure) {
        this.isSecure = secure;
    }

    public boolean isHttpOnly() {
        return isHttpOnly;
    }

    public void setHttpOnly(boolean httpOnly) {
        this.isHttpOnly = httpOnly;
    }

    public SameSiteType getSameSite() {
        return sameSite;
    }

    public void setSameSite(SameSiteType sameSite) {
        this.sameSite = sameSite;
    }

    @Override
    public String toString() {
        StringBuilder cookie = new StringBuilder(super.toString());
        if (domain != null) {
            cookie.append("Domain=").append(domain).append("; ");
        }

        if (path != null) {
            cookie.append("Path=").append(path).append("; ");
        }

        if (maxAge > 0) {
            cookie.append("Max-Age=").append(maxAge).append("; ");
        } else if (expires != null) {
            cookie.append("Expires=").append(DateTimeFormatter.RFC_1123_DATE_TIME.format(expires)).append("; ");
        }

        if (isSecure) {
            cookie.append("Secure; ");
        }

        if (isHttpOnly) {
            cookie.append("HttpOnly; ");
        }

        cookie.append("SameSite=").append(sameSite).append("; ");

        return cookie.toString();
    }
}
