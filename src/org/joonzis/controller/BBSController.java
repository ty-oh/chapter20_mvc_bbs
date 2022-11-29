package org.joonzis.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.joonzis.dao.BDao;
import org.joonzis.model.Paging;
import org.joonzis.service.BBSService;
import org.joonzis.service.BBSServiceImpl;
import org.joonzis.service.CommentService;
import org.joonzis.service.CommentServiceImpl;
import org.joonzis.vo.BVO;
import org.joonzis.vo.CVO;

@WebServlet("/BBSController")
public class BBSController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BBSController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session = request.getSession();
		String cmd = request.getParameter("cmd");
		String resultCmd = "allList";
		String currentPage = "";
		if(cmd != null && !cmd.isEmpty()) {
			resultCmd = cmd;
		}
		System.out.println(resultCmd);
		// 단순 화면 이동 / 데이터 사용 구분
		boolean forwardCheck = false;
		// 이동 경로 path
		String path = "";
		
		BBSService bservice = new BBSServiceImpl();
		CommentService cservice = new CommentServiceImpl();
		
		switch(resultCmd) {
		case "allList" :
			Paging pvo = new Paging();
			pvo.setTotalRecord(bservice.recordCount());
			pvo.setTotalPage();

			currentPage = request.getParameter("currentPage");
			if(currentPage != null && !currentPage.isEmpty()){
				pvo.setNowPage(Integer.parseInt(currentPage));
			}
			
			pvo.setBegin( (pvo.getNowPage()-1) * pvo.getRecordPerPage() + 1);
			pvo.setEnd(pvo.getBegin() + pvo.getRecordPerPage() - 1);
			
			int begin = pvo.getBegin();
			int end = pvo.getEnd();
			
			Map<String, Integer> map = new HashMap<>();
			map.put("begin", begin);
			map.put("end", end);
			
			List<BVO> list = bservice.getList(map);
			request.setAttribute("list", list);
			
			/******* 페이징 처리를 위한 block 계산 ********/
			// 1. beginBlock, endBlock 계산
			pvo.setBeginBlock( (pvo.getNowPage()-1) / pvo.getPagePerBlock() * pvo.getPagePerBlock() + 1);
			pvo.setEndBlock(pvo.getBeginBlock() + pvo.getPagePerBlock() - 1);
			
			// 2. endBlock 조정하기
			if(pvo.getEndBlock() > pvo.getTotalPage()){
				pvo.setEndBlock(pvo.getTotalPage());
			}
			
			// 3. 화면에서 페이징 사용할 수 있도록 객체에 저장
			request.setAttribute("pvo", pvo);
			
			// 4. 게시물이 열린 경우 session에 저장되어야 되는데... 추후 계속
			/*
			 * String open = (String)session.getAttribute("open"); if(open != null){
			 * session.removeAttribute("open"); }
			 */
			forwardCheck = true;
			path = "bbs/allList.jsp";
			break;
		case "view":
			int b_idx = Integer.parseInt(request.getParameter("b_idx"));
			currentPage = request.getParameter("currentPage");
			BVO bvo = bservice.getBbs(b_idx);
			//session open .. 추후
			//수정, 삭제를 위해서 session에 bvo를 저장
			/* session.setAttribute("currentPage", currentPage); */
			request.setAttribute("bbsInfo", bvo);
			List<CVO> cList = cservice.getAllComment(b_idx);
			request.setAttribute("cList", cList);
			
			forwardCheck = true;
			path="bbs/view.jsp";
			break;
		}
		
		if(forwardCheck) {
			request.getRequestDispatcher(path).forward(request, response);
		}else {
			response.sendRedirect(path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}