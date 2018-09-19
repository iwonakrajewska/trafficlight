package com.iwona.trafficlights.app.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.iwona.trafficlights.app.dto.configuration.ProgramConfiguration;
import com.iwona.trafficlights.app.entity.program.Program;
import com.rits.cloning.Cloner;


@RunWith(Parameterized.class)
public class SimpleDtoParameterizedTest {


	private final Object entity;
	private final String entityClassName;

	private static final Collection<String> EXCLUDE_FIELDS = Arrays.asList("chgTs", "chgUsrId", "translations",
			"version", "changedArray", "historyEnabled");

	public SimpleDtoParameterizedTest(final Object entity, final String entityClassName) {
        super();
        this.entity = entity;
        this.entityClassName = entityClassName;
    }

	@Test
	public void testSimpleDomainEntity() throws Exception {
		populateObjectValues(entity);
		Cloner cloner = new Cloner();
		final Object dtoCloned = cloner.deepClone(entity);
		assertNotNull(dtoCloned);
		assertNotNull(entityClassName);
		assertNotNull(entity.toString());
		assertTrue(EqualsBuilder.reflectionEquals(entity, dtoCloned, EXCLUDE_FIELDS));
		assertNotNull(Integer.valueOf(entity.hashCode()));

		assertEquals(entity, entity);
		assertNotEquals(entity, new Object());
	}

	@Parameters(name = "{index}: {1}")
	public static Collection getAllEntities() {
		final Collection<Object[]> c = new ArrayList();
		c.add(createTestParam(new Program()));
		c.add(createTestParam(new ProgramConfiguration()));
		return c;
	}

	private static Object[] createTestParam(final Object o) {
		return new Object[] { o, o.getClass().getSimpleName() };
	}

	/**
	 * populate an object's primitive and Comparable object fields with random-ish
	 * values
	 *
	 * @param source
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("boxing")
	private static void populateObjectValues(final Object source)
			throws IllegalArgumentException, IllegalAccessException {
		final Field[] fields = source.getClass().getDeclaredFields();
		for (final Field field : fields) {
			if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			field.setAccessible(true);
			final Class typeClazz = field.getType();
			final String typeName = typeClazz.getName();
			final short random = (short) (Math.random() * 1000 + 1);
			if (typeClazz.equals(Integer.TYPE) || typeClazz.equals(Short.TYPE) || typeClazz.equals(Long.TYPE)) {
				field.set(source, random);
			} else if (typeClazz.equals(Double.TYPE) || typeClazz.equals(Float.TYPE)) {
				field.set(source, Float.valueOf(random + ".15"));
			} else if (typeClazz.equals(BigDecimal.class)) {
				field.set(source, BigDecimal.valueOf(random));
			} else if (typeClazz.equals(String.class)) {
				field.set(source, "string" + random);
			} else if (typeName.equals("int")) {
				field.setInt(source, random);
			} else if (typeName.equals("short")) {
				field.setShort(source, random);
			} else if (typeName.equals("double")) {
				field.setDouble(source, random);
			} else if (typeName.equals("long")) {
				field.setLong(source, random);
			} else if (typeName.equals("float")) {
				field.setFloat(source, random);
			}
		}
	}

}
