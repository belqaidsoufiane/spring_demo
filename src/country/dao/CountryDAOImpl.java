package country.dao;

import country.model.Country;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.logging.Logger;

@Repository
@Transactional
public class CountryDAOImpl implements CountryDAO {
	private static final Logger LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Country getByCode(String countryCode) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Country c where c.code = :code");
		query.setString("code", countryCode);
		return (Country) query.list().get(0);
	}
	@Override
	public void modifierUnpays(Country country) {
		sessionFactory.getCurrentSession().update(country);
	}
	@Override
	public void enregistrerUnpays(Country country) {
		sessionFactory.getCurrentSession().save(country);
	}

	@Override
	public void supprimerUnpays(Country country) {
		sessionFactory.getCurrentSession().delete(country);
	}

	@Override
	public List<Country> getCountriesByContinent(String continentCode) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Country where continent.code= :code");
		query.setParameter("code", continentCode);
		return (List<Country>) query.list();
	}
}
