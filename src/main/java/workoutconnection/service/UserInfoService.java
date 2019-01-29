package workoutconnection.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import workoutconnection.dao.UserInfoDAO;
import workoutconnection.entities.Measurement;
import workoutconnection.entities.UserGoals;
import workoutconnection.models.Exercise;

@Service
public class UserInfoService implements IUserInfo {

	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@Override
	public List<UserGoals> getAllGoals(int userid) {
		return userInfoDAO.getAllGoals(userid);
	}

	@Override
	public Object getWorkouts(int userid) 
			throws JsonParseException, JsonMappingException, IOException {
		return userInfoDAO.getWorkouts(userid);
	}

	@Override
	public Object getUserInfo(int userid) 
			throws JsonParseException, JsonMappingException, IOException {
	
		Map<Object,Object> userInfo = new HashMap<Object,Object>();
		userInfo.put("workouts",this.getWorkouts(userid));
		userInfo.put("goals",this.getAllGoals(userid));
		userInfo.put("measurements",this.getMeasurement(userid));
		
		return userInfo;
	}

	@Override
	public void saveWorkouts(Map<String, List<Exercise>> workouts, int userid) 
			throws JsonGenerationException, JsonMappingException, IOException {
		userInfoDAO.saveWorkouts(workouts, userid);
	}

	@Override 
	public void deleteWorkout(List<Exercise> workout, int userid) 
			throws JsonParseException, JsonMappingException, IOException {
		userInfoDAO.deleteWorkout(workout, userid);
	}
	
	@Override
	public List<Measurement> getMeasurement(int userid) {
		return userInfoDAO.getMeasurement(userid);
	}

	@Override
	public void insertMeasurement(Measurement measurement, int userid) {
		userInfoDAO.insertMeasurement(measurement, userid);
		
	}

	@Override
	public void updateMeasurement(Measurement measurement) {
		userInfoDAO.updateMeasurement(measurement);
		
	}

	@Override
	public boolean deleteMeasurement(Measurement measurement) {
		return userInfoDAO.deleteMeasurement(measurement);
		
	}

	@Override
	public void insertGoals(UserGoals userGoals, int userid) {
		userInfoDAO.insertGoals(userGoals, userid);
		
	}

	@Override
	public void updateGoals(UserGoals userGoals) {
		userInfoDAO.updateGoals(userGoals);
		
	}

	@Override
	public void deleteGoals(UserGoals userGoals) {
		userInfoDAO.deleteGoals(userGoals);
		
	}


	
}