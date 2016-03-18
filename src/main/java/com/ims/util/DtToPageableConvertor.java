package com.ims.util;

import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

public class DtToPageableConvertor {

	@SuppressWarnings("static-access")
	public static Pageable convertToPageable(DatatablesCriterias criterias) {
		Direction s = null;
		String properties = null;
		for (ColumnDef criteria : criterias.getColumnDefs()) {
			if (criteria.getSortDirection() != null) {
				s = s.valueOf(criteria.getSortDirection().toString());
				properties = criteria.getName();
				break;
			}
		}
		if (properties != null)
			return new PageRequest(criterias.getDisplayStart() / criterias.getDisplaySize(), criterias.getDisplaySize(), s,
					properties);
		else
			return new PageRequest(criterias.getDisplayStart() / criterias.getDisplaySize(), criterias.getDisplaySize(), s);

	}
}
