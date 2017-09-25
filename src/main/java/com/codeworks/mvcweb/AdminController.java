package com.codeworks.mvcweb;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codeworks.helper.DateHelper;
import com.codeworks.model.AdminUser;
import com.codeworks.model.Student;
import com.codeworks.service.AdminUserService;
import com.codeworks.service.StudentService;
import com.codeworks.util.RandomInviteCodeGenerator;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminUserService adminUserService;
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(value="/studList",method=RequestMethod.GET)
	public ResponseEntity<List<Student>> getActiveStudents(HttpServletRequest request) {
		
		HttpSession httpSession = request.getSession();
		
		Long adminId = Long.parseLong(httpSession.getAttribute("adminId").toString());
		String emailId = httpSession.getAttribute("adminEmailId").toString();
		
		
		List<Student> studentList= studentService.getActiveStudents(adminId,emailId);
		
		return new ResponseEntity(studentList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/studAdd",method=RequestMethod.POST)
	//public ResponseEntity<List<Student>> registerStudent(@ModelAttribute("studForm") Student studentForm, BindingResult bindingResult,Model model, String error, String logout,HttpServletRequest request) {
	public String registerStudent(@ModelAttribute("studForm") Student studentForm, BindingResult bindingResult,Model model, String error, String logout,HttpServletRequest request) {
		
		HttpSession httpSession = request.getSession();
		
		Long adminId = Long.parseLong(httpSession.getAttribute("adminId").toString());
		String emailId = httpSession.getAttribute("adminEmailId").toString();
		
		//studentForm.setCreationDate(new Date());
		studentForm.setActive(Boolean.TRUE);
		studentForm.setCertified(Boolean.FALSE);
		studentForm.setCreatedBy(emailId);		
		
		String inviteCode = RandomInviteCodeGenerator.randomString(studentForm.getFirstName(), studentForm.getLastName());
		
		studentForm.setInviteCode(inviteCode);
		if(StringUtils.isEmpty(studentForm.getMiddleName())){
			studentForm.setMiddleName("");
		}
		studentForm.setCreationDate(DateHelper.getCurrentDateInUTC());
		studentForm.setTargetDate(DateHelper.createTargetDate(studentForm.getCreationDate(), 90));
		
		List<Student> studentList= studentService.registerStudent(adminId,emailId, studentForm);
		
		return "adminHome";//new ResponseEntity(studentList, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"","/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model,HttpServletRequest request) {
		request.getSession().setAttribute("namedPair",RandomInviteCodeGenerator.SECRET_KEY);
		request.setAttribute("namedPair2",RandomInviteCodeGenerator.SECRET_KEY);
        return "adminLogin";
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.ALL_VALUE)
    public String login(@ModelAttribute("userForm") AdminUser userForm, BindingResult bindingResult,Model model, String error, String logout,HttpServletRequest request)  {
		
		String decryptPassword = " ";
		try {
			decryptPassword = RandomInviteCodeGenerator.decryptPwd(userForm.getAdminPwd());
		} catch(Exception exception){
			exception.printStackTrace();
		}
		List<AdminUser> adminUserList = adminUserService.loginValidation(userForm.getAdminEmail(), decryptPassword);
		String returnString = "";
		if(!adminUserList.isEmpty()){
			System.out.println(" data exists");
			
			request.setAttribute("name",adminUserList.get(0).getFirstName()+" "+adminUserList.get(0).getLastName());
			request.setAttribute("tab", "menu1");
			
			request.getSession().setAttribute("adminId",adminUserList.get(0).getAdminId());
			request.getSession().setAttribute("adminEmailId",adminUserList.get(0).getAdminEmail());
			
			returnString = "adminHome";
		} else {
			System.out.println(" empty ");
			request.setAttribute("invalidUser","Invalid Email Id or password");
			returnString = "adminLogin";
		}
		return returnString;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ResponseEntity<List<AdminUser>> getActiveAdminUsers(HttpServletRequest request) {

		List<AdminUser> adminUserList= adminUserService.getActiveAdminUsers();
		return new ResponseEntity(adminUserList, HttpStatus.OK);
	}
}
