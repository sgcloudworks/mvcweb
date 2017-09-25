package com.codeworks.mvcweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeworks.helper.DateHelper;
import com.codeworks.model.Student;
import com.codeworks.model.User;
import com.codeworks.service.UserService;

@Controller
@Secured("ROLE_USER")
public class UserController {
   /* @Autowired
    private UserService userService;*/

    /*@Autowired
    private SecurityService securityService;*/

    /*@Autowired
    private UserValidator userValidator;*/
	
	@Context
    private HttpServletRequest httpRequest;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/regUser"}, method = RequestMethod.POST, produces = MediaType.ALL_VALUE)
	public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,Model model, String error, String logout,HttpServletRequest request) {
		
		String redirect = "welcome";
		HttpSession session = request.getSession();
		
		//User registration code
		Student studentData = (Student)session.getAttribute(userForm.getEmailId());
		if(studentData != null){
			
			String inviteCodeString = request.getParameter("inviteCode");
			if(StringUtils.equalsIgnoreCase(studentData.getInviteCode(), inviteCodeString)){
				userForm.setStudentId(studentData.getStudentId());
				int rowInserted = userService.registerUser(userForm);
				if(rowInserted>0){
					redirect = "welcome";//"redirect:/TabsNpills.html";
					request.setAttribute("msg","Self Registration is successful. Please login with your registered Email and Password");
				} else {
					request.setAttribute("errorMessage", "User self registration failed. Please contact system administrator");
				}
				
			}
		}    		
		return redirect;
	}
	
	@RequestMapping(value="/userExists",method=RequestMethod.GET)
	public ResponseEntity<String> loadUser(@RequestParam("emailId") String emailId,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		User user = userService.loadUser(emailId);
		String message = "invalid";
		if(user.getUserId()>0){
			message = "valid";
			session.setAttribute(emailId+":user", user);
		} else {
			message = user.getAdminMessage();
			request.setAttribute("errorMessage",user.getAdminMessage());
		}		
		
		return new ResponseEntity(message, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = {"/reset"}, method = RequestMethod.POST, produces = MediaType.ALL_VALUE)
    public String updatePwd(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,Model model, String error, String logout,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(userForm.getEmailId()+":user");
		
		if(user!=null){
			if(user.getUserId()>0){
				userForm.setModifiedBy(user.getCreatedBy());
				userForm.setModifiedDate(DateHelper.getCurrentDateInUTC());
				request.setAttribute("msg","Password changed successfully. Please login with the new password");
				userService.resetPwd(userForm);
			} else {
				request.setAttribute("errorMessage",user.getAdminMessage());
			}
		}
		
		return "welcome";
	}
    	
	
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST, produces = MediaType.ALL_VALUE)
    public String login(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,Model model, String error, String logout,HttpServletRequest request) {
    	
		HttpSession session = request.getSession();
		String redirect = "welcome";

		//String requestDispatcherPath = request.getServletPath();
		
		User newUser = new User();
		// Login code
		newUser = userService.verifyLogin(userForm);
		if (newUser.getUserId() < 0) {
			request.setAttribute("errorMessage", newUser.getAdminMessage());
		} else {
			redirect = "userHome";//"redirect:/TabsNpills.html";
		}
		System.out.println("debug ");

		return redirect;// "redirect:/TabsNpills.html";//"login";
    }

    @RequestMapping(value = {"","/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}