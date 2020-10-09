package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import study.querydsl.Entity.Hello;
import study.querydsl.Entity.QHello;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
class QuerydslApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QHello qHello = new QHello("h");
	    Hello result = 	query.
				selectFrom(qHello)
				.fetchOne();

		assertThat(hello).isEqualTo(result);
		assertThat(hello.getId()).isEqualTo(result.getId());

	}
}
