package com.infoshareacademy.api;

import com.infoshareacademy.dao.ComputerDao;
import com.infoshareacademy.model.Computer;
import com.infoshareacademy.model.Student;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/computers")
public class ComputerApi {

  @Inject
  ComputerDao computerDao;

  @Path("")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  @Transactional
  public String addComputer(JsonObject jsonObject) {
    Computer computer = new Computer();
    computer.setName(jsonObject.getString("name"));
    computer.setOperatingSystem(jsonObject.getString("os"));
    computerDao.save(computer);

    List<Computer> computers = computerDao.findAll();
    return computers.stream()
      .map(Computer::toString)
      .collect(Collectors.toList())
      .toString();
  }
}
