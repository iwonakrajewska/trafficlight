package com.iwona.trafficlights.app.dao.program;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.iwona.trafficlights.app.dao.HibernateDao;
import com.iwona.trafficlights.app.entity.program.Program;
import com.iwona.trafficlights.app.entity.program.Schedule;

@Repository
public class ProgramDaoImpl extends HibernateDao implements ProgramDao {

	private static final Logger LOGGER = LogManager.getLogger(ProgramDaoImpl.class);

	@Override
	public List<Program> loadAllPrograms() {
//		TypedQuery<Program> query = getCurrentSession().createQuery("FROM Program");
//		List<Program> programList = query.getResultList();
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Program> criteriaQuery = builder.createQuery(Program.class);
		Root<Program> root = criteriaQuery.from(Program.class);
		criteriaQuery.select(root);
		Query<Program> query = getCurrentSession().createQuery(criteriaQuery);
		List<Program> programList = query.getResultList();
		LOGGER.info("Programs loded: " + programList);
		return programList;
	}

	@Override
	public List<Schedule> loadProgramSchedule() {
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Schedule> criteriaQuery = builder.createQuery(Schedule.class);
		Root<Schedule> root = criteriaQuery.from(Schedule.class);
		criteriaQuery.select(root);
		Query<Schedule> query = getCurrentSession().createQuery(criteriaQuery);
		List<Schedule> scheduleList = query.getResultList();
		LOGGER.info("Schedules loded: " + scheduleList);
		return scheduleList;
	}

}