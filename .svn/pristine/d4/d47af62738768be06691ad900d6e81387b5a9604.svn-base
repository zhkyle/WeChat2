package com.scie.wechat.recruitment;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.scie.wechat.bean.Student;
import com.scie.wechat.util.HibernateSessionFactory;

public class RecruitmentStudent {
	public boolean recruit(String snum ,String sname, String sclass, String sphone,String sdep) {
		boolean isRecruited = false;
		try {
			Student s = new Student(snum, sclass, sname, sphone, sdep);
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.save(s);
			session.getTransaction().commit();
			session.flush();
			HibernateSessionFactory.closeSession();
			isRecruited = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return isRecruited;
	}
}
