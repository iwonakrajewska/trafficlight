package com.iwona.trafficlights.app.dao.program;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iwona.trafficlights.app.entity.program.Program;
import com.iwona.trafficlights.app.entity.program.Schedule;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProgramDaoImplTest {

	@InjectMocks
	private ProgramDaoImpl programDao;
	@Mock
	private SessionFactory sessionFactory;
	@Mock
	private Session session;
	@Mock
	private CriteriaBuilder builder;
	@Mock
	private CriteriaQuery criteriaQuery;
	@Mock
	private Root root;
	@Mock
	private Query query;

	private List programList;

	@Before
	public void setUp() {
		programList = new ArrayList<>();
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void testLoadPrograms() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.getCriteriaBuilder()).thenReturn(builder);
		when(builder.createQuery(Program.class)).thenReturn(criteriaQuery);
		when(criteriaQuery.from(Program.class)).thenReturn(root);
		when(session.createQuery(criteriaQuery)).thenReturn(query);
		when(query.getResultList()).thenReturn(programList);
		assertNotNull(programDao.loadAllPrograms());
	}

	@Test
	public void testLoadSchedules() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.getCriteriaBuilder()).thenReturn(builder);
		when(builder.createQuery(Schedule.class)).thenReturn(criteriaQuery);
		when(criteriaQuery.from(Schedule.class)).thenReturn(root);
		when(session.createQuery(criteriaQuery)).thenReturn(query);
		when(query.getResultList()).thenReturn(programList);
		assertNotNull(programDao.loadProgramSchedule());
	}

}
