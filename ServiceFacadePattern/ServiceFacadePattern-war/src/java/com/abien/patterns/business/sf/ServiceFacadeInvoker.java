package com.abien.patterns.business.sf;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
This file is part of javaee-patterns.

javaee-patterns is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

javaee-patterns is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.opensource.org/licenses/gpl-2.0.php>.

* Copyright (c) 16. June 2009 Adam Bien, blog.adam-bien.com
* http://press.adam-bien.com
*/
public class ServiceFacadeInvoker extends HttpServlet {
   
    @EJB
    private BookOrderingServiceLocal bookOrderingServiceLocal;
    @EJB
    private BookOrderingServiceRemote bookOrderingServiceRemote;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ParameterTest testParameter = new ParameterTest(42);
        Object localResult = bookOrderingServiceLocal.referenceTest(testParameter);
        Object remoteResult = bookOrderingServiceRemote.referenceTest(testParameter);
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServiceFacadeInvoker</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("The same reference (local service)?: " + (testParameter == localResult) + " <br/>");
            out.println("The same reference (remote service)?: " + (testParameter == remoteResult) + " <br/>");
            out.println("</body>");
            out.println("</html>");
        } finally { 
            out.close();
        }
    } 


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}