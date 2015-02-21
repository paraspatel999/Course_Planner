package web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Data;
import web.model.QuarterPlan;

@WebServlet("/next")
public class next extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public next() {
		super();

	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		 
		
		request.getRequestDispatcher( "/WEB-INF/next.jsp" ).forward(
	            request, response );
		
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Data> d=(List<Data>)request.getSession().getAttribute("Sub_entries");
		
		int index=Integer.valueOf(request.getParameter("hid"));
		List<QuarterPlan> q=(List<QuarterPlan>)request.getSession().getAttribute("Quart");
		QuarterPlan qa=new QuarterPlan(index+1,d);
		
		if(q.get(index).getNextquarter().startsWith("F"))
			 qa.setYear(q.get(index).getYear()+1);
		else
			 qa.setYear(q.get(index).getYear());
		if(q.get(index).getWeek()>37)
			qa.setWeek(5);
		else
			qa.setWeek(q.get(index).getWeek()+12);
		
		
		
		if(request.getParameterValues("cp")==null)
		{
			
			if(request.getParameter("butt") != null)
			{
				
				q.get(index).setTaken(new ArrayList<Data>());
				
				 request.getSession().setAttribute("Quart",q);
				 
				 response.sendRedirect("finish");
				 return;
			} 
			qa.setSelect(qa.get(q.get(index).getSub()));
			qa.setSub(q.get(index).getSub());
			q.get(index).setTaken(new ArrayList<Data>());
			q.add(qa);
			request.getSession().setAttribute("Quart",q);
			 response.sendRedirect("next?name="+qa.getNextquarter()+"");
			
			return;
		}
		List<String> sub=new  LinkedList<String>(Arrays.asList(request.getParameterValues("cp")));
		List<Data> da=new ArrayList<Data>();
		
		
		for(Data ds: d)
			if(sub.contains(ds.getCode()))
				da.add(ds);
		
		q.get(index).setTaken(da);
		
		q.get(index).getSub().addAll(sub);
		if(request.getParameter("butt") != null)
		{
			 request.getSession().setAttribute("Quart",q);
			 
			 response.sendRedirect("finish");
			 return;
		}
		if(qa.get(q.get(index).getSub()).size()!=0)
		{
			
		 qa.setSelect(qa.get(q.get(index).getSub()));
		 qa.setTaken(da);
		 qa.setSub(q.get(index).getSub());
		
		 
		
		 q.add(qa);
		 request.getSession().setAttribute("Quart",q);
		 
		 response.sendRedirect("next?name="+qa.getNextquarter()+"");
		}
		else
		{
			 request.getSession().setAttribute("Quart",q);
			 response.sendRedirect("finish");
			
		}
		
	}

}
