package com.ims.domain.cfg;

import com.ims.util.Constants;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * The Class EMCustomNamingStrategy.
 */
public class EMCustomNamingStrategy extends ImprovedNamingStrategy {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * 
	 * @see org.hibernate.cfg.ImprovedNamingStrategy#columnName(java.lang.String) */
	@Override
	public String columnName(String columnName) {
		return super.columnName(columnName).toUpperCase();
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return super.propertyToColumnName(propertyName).toUpperCase();
	}

	/* (non-Javadoc)
	 * 
	 * @see org.hibernate.cfg.ImprovedNamingStrategy#tableName(java.lang.String) */
	@Override
	public String tableName(String tableName) {
		if (tableName.contains("AUD") || tableName.contains("aud"))
			if (tableName.contains(Constants.APPLICATION_SCHEMA_PREFIX)
					|| tableName.contains(Constants.APPLICATION_SCHEMA_PREFIX.toLowerCase()))
				return super.tableName(tableName).toUpperCase();
			else
				return (Constants.APPLICATION_SCHEMA_PREFIX + super.tableName(tableName)).toUpperCase();
		else
			return (Constants.APPLICATION_SCHEMA_PREFIX + super.tableName(tableName)).toUpperCase();
	}
	
}