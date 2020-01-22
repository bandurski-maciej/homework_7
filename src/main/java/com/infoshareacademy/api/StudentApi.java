package com.infoshareacademy.api;

import com.infoshareacademy.dao.StudentDao;
import com.infoshareacademy.mapper.StudentMapper;
import com.infoshareacademy.model.Student;
import com.infoshareacademy.service.StudentService;
import com.infoshareacademy.util.JsonHelper;
import com.infoshareacademy.web.StudentServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/students")
public class StudentApi {

  private Logger LOG = LoggerFactory.getLogger(StudentServlet.class);

  @Inject
  StudentDao studentDao;

  @Path("")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Transactional
  public String returnsStudentsList() {
    List<Student> students = studentDao.findAll();
    return students.stream()
      .map(Student::toString)
      .collect(Collectors.toList())
      .toString();
  }

  @Path("/{name}")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Transactional
  public String returnsStudentByName(@PathParam("name") String name) {
    List<Student> students = studentDao.findByName(name);
    return students.stream()
      .map(Student::toString)
      .collect(Collectors.toList())
      .toString();
  }

}
