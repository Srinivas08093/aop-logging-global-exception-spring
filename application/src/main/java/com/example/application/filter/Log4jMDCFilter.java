package com.example.application.filter;

import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * This filter will execute once for request dispatch for more details please
 * look this Spring filter{@code OncePerRequestFilter} It will checks the MDC
 * Token in request pay load if exists it uses the same token else it creates
 * new token with help of {@code UUID} class
 * 
 * @author Srinivas Nangana
 *
 */
public class Log4jMDCFilter extends OncePerRequestFilter {

	private String responseHeader;
	private String mdcTokenKey;
	private String requestHeader;

	public Log4jMDCFilter(String responseHeader, String mdcTokenKey, String requestHeader) {

		this.requestHeader = requestHeader;
		this.mdcTokenKey = mdcTokenKey;
		this.responseHeader = responseHeader;
	}

	public Log4jMDCFilter() {
		super();
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain) throws java.io.IOException, ServletException {
		try {
			final String token;
			if (!isEmpty(requestHeader) && !isEmpty(request.getHeader(requestHeader))) {
				token = request.getHeader(requestHeader);
			} else {
				token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
			}
			MDC.put(mdcTokenKey, token);
			if (!isEmpty(responseHeader)) {
				response.addHeader(responseHeader, token);
			}
			chain.doFilter(request, response);
		} finally {
			MDC.remove(mdcTokenKey);
		}
	}
	
	  /**
     * Checks if a String is empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return true if the String is empty or null
     * @author Srinivas Nangana
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
	
}