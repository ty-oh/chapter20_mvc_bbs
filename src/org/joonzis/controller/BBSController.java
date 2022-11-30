package org.joonzis.controller;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joonzis.model.Paging;
import org.joonzis.service.BBSService;
import org.joonzis.service.BBSServiceImpl;
import org.joonzis.service.CommentService;
import org.joonzis.service.CommentServiceImpl;
import org.joonzis.vo.BVO;
import org.joonzis.vo.CVO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/BBSController")
public class BBSController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BBSController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String realPath = request.getServletContext().getRealPath("/upload");
		MultipartRequest mr = null;
		HttpSession session = request.getSession();
		BBSService bservice = new BBSServiceImpl();
		CommentService cservice = new CommentServiceImpl();
		
		boolean forwardCheck = false;
		String path = "";
		
		String cmd = request.getParameter("cmd");
		if (cmd == null) {
			mr = new MultipartRequest(
					 request,
					 realPath,
					 1024*1024*10,
					 "utf-8",
					 new DefaultFileRenamePolicy()
					 );
			cmd = mr.getParameter("cmd");
		}
		
		BVO bvo = null;
		CVO cvo = null;
		String currentPage = "";
		
		String resultCmd = "allList";
		if(cmd != null && !cmd.isEmpty()) {
			resultCmd = cmd;
		}
		
		switch(resultCmd) {
		case "remove_page":
			path = "bbs/remove_page.jsp";
			break;
		
		case "update_page":
			path = "bbs/update_page.jsp";
			break;
		
		case "insert_page":
			path = "bbs/insert_page.jsp";
			break;
			
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
			
			forwardCheck = true;
			path = "bbs/allList.jsp";
			break;
			
		case "view":
			int b_idx = Integer.parseInt(request.getParameter("b_idx"));
			currentPage = request.getParameter("currentPage");
			// 글을 처음 여는 경우 조회수 증가. 1시간동안 중복 조회 안됨.
			String opened = (String)session.getAttribute("open"+b_idx);
			if (opened == null) {
				bservice.updateHit(b_idx);
				session.setAttribute("open"+b_idx, "open");
				session.setMaxInactiveInterval(60*60);
			}
			
			bvo = bservice.getBbs(b_idx);
			session.setAttribute("bbsInfo", bvo);
			session.setAttribute("currentPage", currentPage);
			
			List<CVO> cList = cservice.getAllComment(b_idx);
			request.setAttribute("cList", cList);
			
			forwardCheck = true;
			path="bbs/view.jsp";
			break;
		
		case "insert":
			bvo = new BVO();
			bvo.setWriter(mr.getParameter("writer"));
			bvo.setTitle(mr.getParameter("title"));
			bvo.setPw(mr.getParameter("pw"));
			bvo.setContent(mr.getParameter("content"));
			bvo.setIp(InetAddress.getLocalHost().getHostAddress());

			if (mr.getFile("filename") != null) {
				bvo.setFilename(mr.getFilesystemName("filename"));
			} else {
				bvo.setFilename("");
			}
			
			int result = bservice.insertBbs(bvo);

			path="/chapter20_mvc_bbs/BBSController?cmd=allList";
			break;
			
		case "update":			
			bvo = new BVO();
			File newfile = mr.getFile("filename");
			String oldfile = mr.getParameter("oldfile");
			if(newfile != null) {		// 새 첨부 파일 O
				if(oldfile != null) {	// 기존 첨부 파일 O
					File removeFile = new File(realPath + "/" + oldfile);
					if (removeFile.exists()) {	// 기존 첨부파일 유무 확인
						removeFile.delete();	// 기존 첨부 파일 삭제
					}
				}
				bvo.setFilename(newfile.getName()); // 새 첨부 파일 이름 가져오기
			} else {	// 새 첨부파일 X
				if(oldfile != null) {		// 기존 첨부 파일 O
					bvo.setFilename(oldfile);
				} else {
					bvo.setFilename("");	// 새 첨부파일 X, 기존 첨부파일 X
				}
			}
			
			bvo.setB_idx(Integer.parseInt(mr.getParameter("b_idx")));
			bvo.setWriter(mr.getParameter("writer"));
			bvo.setTitle(mr.getParameter("title"));
			bvo.setContent(mr.getParameter("content"));
			
			result = bservice.updateBbs(bvo);
			
			currentPage = mr.getParameter("currentPage");
			path="/chapter20_mvc_bbs/BBSController?cmd=view&b_idx="+bvo.getB_idx() + "&currentPage=" + currentPage;
			break;
			
		case "remove":
			bvo = (BVO)session.getAttribute("bbsInfo");
			currentPage = request.getParameter("currentPage");
			
			result = bservice.removeBbs(bvo.getB_idx());
			path="/chapter20_mvc_bbs/BBSController?cmd=allList&currentPage="+currentPage;
			break;
		
		case "insert_comment":
			cvo = new CVO();
			cvo.setWriter(request.getParameter("writer"));
			cvo.setContent(request.getParameter("content"));
			cvo.setPw(request.getParameter("pw"));
			cvo.setIp(Inet4Address.getLocalHost().getHostAddress());
			cvo.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
			
			result = cservice.insertComment(cvo);
			path= "/chapter20_mvc_bbs/BBSController?cmd=view&b_idx="+cvo.getB_idx();
			break;
		
		case "remove_comment":
			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			b_idx = Integer.parseInt(request.getParameter("b_idx"));
			
			result = cservice.removeComment(c_idx);
			path= "/chapter20_mvc_bbs/BBSController?cmd=view&b_idx="+b_idx;
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