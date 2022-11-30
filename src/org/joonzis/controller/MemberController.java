package org.joonzis.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joonzis.service.MemberService;
import org.joonzis.service.MemberServiceImpl;
import org.joonzis.vo.MVO;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		String cmd = request.getParameter("cmd");
		String path = "";
		MVO mvo = null;
		boolean forwardCheck = false;
		MemberService mservice = new MemberServiceImpl();

		int result;
		
		switch (cmd) {
		case "join_member_page":
			path="bbs/join_member_page.jsp";
			break;
			
		case "join_member":
			String id = request.getParameter("id");
			int idCheck = mservice.idCheck(id);

			if (idCheck == 0) {
				mvo = new MVO();
				mvo.setM_id(request.getParameter("id"));
				mvo.setM_pw(request.getParameter("pw"));
				mvo.setM_name(request.getParameter("name"));
				mvo.setM_email(request.getParameter("email"));
				mvo.setM_self(request.getParameter("self"));
				result = mservice.joinMember(mvo);

				path="bbs/login_page.jsp";
				
			} else {
				path="bbs/join_fail.jsp";
			}
			break;
		}

		if (forwardCheck) {
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			response.sendRedirect(path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
