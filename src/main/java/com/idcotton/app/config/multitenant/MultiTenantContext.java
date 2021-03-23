package com.idcotton.app.config.multitenant;

import java.io.Serializable;

public class MultiTenantContext implements Serializable {

	private static final long serialVersionUID = 8773459446285987014L;
	private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

	public static void setTenantId(String tenantId) {
		CONTEXT.set(tenantId);
	}

	public static String getTenantId() {
		return CONTEXT.get();
	}

	public static void clear() {
		CONTEXT.set(null);
	}
}
