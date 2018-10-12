package workoutconnection.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import workoutconnection.entities.User;

@Transactional
@Repository
public class UserDAO  implements IUserDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public User save(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public List<User> findAll() {
		
		String HQL = "From User";
		
		@SuppressWarnings("unchecked")
		List<User> resultList = (List<User>)entityManager.createQuery(HQL).getResultList();
		return resultList;
	}

	@Override
	public void deleteById(int id) {
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);
	}

	@Override
	public User findByUsername(String username) {	
		String HQL = "From User where user_name = '"+username+"'";		
		User user = (User) entityManager.createQuery(HQL).getSingleResult();
		return user;
	}

	@Override
	public boolean isUserExist(String username) {
		String HQL = "From User where user_name = '"+username+"'";	

		int user = entityManager.createQuery(HQL).getResultList().size();
		return user != 0 ? true : false;
	}

	@Override
	public boolean isEmailExist(String email) {
		String HQL = "From User where email = '"+email+"'";	

		int user = entityManager.createQuery(HQL).getResultList().size();
		return user != 0 ? true : false;
	}
	


}
