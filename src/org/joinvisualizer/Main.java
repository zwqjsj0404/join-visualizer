package org.joinvisualizer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Hannes John
 * @since 26.05.2010 12:53:24
 */
public class Main {
	private static final DotFile dotFile = new DotFileImpl();
	public static void main(final String[] args) {
		try {
			final Writer writer = new FileWriter("select.dot");
			table("assortment_area", "aa");
			table("floor_area", "fa");
			constraint("aa.floor_area_oid = fa.oid", "aa", "floor_area_oid", "=", "fa", "oid");
			table("store_floor", "sf");
			constraint("fa.store_floor_oid = sf.oid", "fa", "store_floor_oid", "=", "sf", "oid");
			table("store_area", "sa");
			constraint("sf.store_area_oid = sa.oid", "sf", "store_area_oid", "=", "sa", "oid");
			table("store", "s");
			constraint("sa.store_oid = s.oid", "sa", "store_oid", "=", "s", "oid");
			table("planning_period", "pp");
			constraint("sa.planning_period_oid = pp.oid", "sa", "planning_period_oid", "=", "pp", "oid");
			table("floor_area_type", "fat");
			constraint("fa.floor_area_type_oid = fat.oid", "fa", "floor_area_type_oid", "=", "fat", "oid");
			table("assortment_area_type", "aat");
			constraint("aa.assortment_area_type_oid = aat.oid", "aa", "assortment_area_type_oid", "=", "aat", "oid");
			table("ah_assignment", "aha");
			constraint("aa.ah_assignment_oid = aha.oid", "aa", "ah_assignment_oid", "=", "aha", "oid");
			table("ah_item", "ahi");
			constraint("aha.ah_item_oid = ahi.oid", "aha", "ah_item_oid", "=", "ahi", "oid");
			constraint("s.store_no = 9330");
			constraint("pp.description = '201309'");
			constraint("sf.store_floor_code = 'EG'");
			constraint("fat.description = 'Verkaufsfläche'");
			constraint("aat.description = 'Themenfläche'");
			constraint("ahi.ah_item_code = '3006'");
			dotFile.paint(writer);
			writer.close();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void constraint(String expression) {
		dotFile.constraint(expression);
	}

	private static void constraint(final String expression, final String table1Alias, final String field1Name, final String operator, final String table2Alias, final String field2Name) {
		dotFile.constraint(expression, table1Alias, field1Name, operator, table2Alias, field2Name);
	}
	private static void table(final String name, final String alias) {
		dotFile.table(name, alias);
	}
}
