package workoutconnection.interfaces;

import java.util.List;

import workoutconnection.entities.UserGoals;

public interface IUserGoalsDAO {
	
	List<UserGoals> getAllGoals(int userid);
}
