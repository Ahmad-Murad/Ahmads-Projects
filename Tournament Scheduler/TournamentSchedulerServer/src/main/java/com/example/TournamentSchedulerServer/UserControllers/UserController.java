package com.example.TournamentSchedulerServer.UserControllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Optional;

@RestController
class UserController 
{

	@Autowired
	UserRepository userRepository;

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, path = "/User/new",produces = { "application/json"})
	@GeneratedValue
	public String saveAccount(@RequestBody User user)
	{

			userRepository.save(user);
			return "true";

	}
	/*@RequestMapping(method = RequestMethod.POST, path = "/User/new/{uName}/{password}/{firstName}/{lastName}/{email}/{zipCo}",produces = { "application/json"})
	@GeneratedValue
	public String saveAccount(
			@PathVariable("uName") String uName, @PathVariable("password") String password,
			@PathVariable("firstName") String fName, @PathVariable("lastName") String lName,
			@PathVariable("email") String email, @PathVariable("zipCo") int zip)
	{
		User user = new User();
		user.setUserName(uName);
		user.setPassword(password);
		user.setEmail(email);
		user.setZipCode(zip);
		user.setFirstName(fName);
		user.setLastName(lName);
		userRepository.save(user);
		return "New account " + user.getUserName() + " Saved";
	}*/


	@RequestMapping(method = RequestMethod.GET, path = "/Users",produces = { "application/json"})
	public List<User> getAllUsers()
	{
		logger.info("Entered into Controller Layer");
		List<User> results = userRepository.findAll();
		logger.info("Number of Records Fetched:" + results.size());
		return results;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/Users/logIn/{userName}/{password}",produces = { "application/json"})
	@GeneratedValue
	@ResponseBody
	@CrossOrigin
	public String validateAccount(
			@PathVariable("userName") String uName, @PathVariable("password") String password)
	{
		try {
			String passwordCheck = userRepository.getPassword(uName);
			if (passwordCheck.equals(password))
			{
				return "true";
			}
			else
			{
				return "false";
			}
		}
		catch (NullPointerException e)
		{
			return "false";
		}

	}

	@RequestMapping(method = RequestMethod.GET, path = "/User/{id}",produces = { "application/json"})
	public Optional<User> findUserById(@PathVariable("id") String id) 
	{
		logger.info("Entered into Controller Layer");
		Optional<User> results = userRepository.findById(id);
		return results;
	}
}
