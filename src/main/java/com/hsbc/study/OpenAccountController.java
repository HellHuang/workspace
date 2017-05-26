package com.hsbc.study;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hsbc.study.dao.DBhelper;
import com.mysql.jdbc.Connection;
import com.hsbc.study.dao.OpenAccountDao;

@Controller
public class OpenAccountController{
	@RequestMapping(value="/createaccount", method = RequestMethod.POST)
	public ModelAndView openAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		String username=request.getParameter("username");
		String userid=request.getParameter("userid");
		String cardpin=request.getParameter("cardpin");
		
		OpenAccountDao openaccountdao=new OpenAccountDao();
		
		Map<String ,Object> model=new HashMap<String,Object>();
		String judgeuser=openaccountdao.judgeUser(userid, username);
		
		if(judgeuser.equals("userid and username are matched")){
			String judge=openaccountdao.insertInfoExist(userid, username, cardpin);
			if(!judge.equals("")){
				model.put("user-id", userid);
				model.put("user-name",username);
				model.put("card-pin", cardpin);
				model.put("cardnumber",judge);
				return new ModelAndView("opensuccess",model);
			}
			else{
				model.put("message", "Unknow error.");
				model.put("link", "toopenaccount");
				return new ModelAndView("cardNotFound",model);
			}
		}
		else if(judgeuser.equals("new user")){
			String judge=openaccountdao.insertInfoNew(userid, username, cardpin);
			if(!judge.equals("")){
				model.put("user-id", userid);
				model.put("user-name",username);
				model.put("card-pin", cardpin);
				model.put("cardnumber",judge);
				return new ModelAndView("opensuccess",model);
			}
			else{
				model.put("message", "Unknow error.");
				model.put("link", "toopenaccount");
				return new ModelAndView("cardNotFound",model);
			}
		}
		else if(judgeuser.equals("userid and username are not matched")){
			model.put("message", "Userid and username are not matched.");
			model.put("link", "toopenaccount");
			return new ModelAndView("cardNotFound",model);
		}
		else{
			model.put("message", "Unknow error");
			model.put("link", "toopenaccount");
			return new ModelAndView("cardNotFound",model);
		}
	}
}