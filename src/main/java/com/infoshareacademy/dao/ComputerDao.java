package com.infoshareacademy.dao;

import javax.ejb.Stateless;

@Stateless
public class ComputerDao {

  @PersistenceContext
  private EntityManager entityManager;

  public Long save(Computer c) {
    entityManager.persist(c);
    return c.getId();
  }

  public Computer update(Computer c) {
    return entityManager.merge(c);
  }

  public void delete(Long id) {
    final Computer c = entityManager.find(Computer.class, id);
    if (c != null) {
      entityManager.remove(c);
    }
  }

  public Computer findById(Long id) {
    return entityManager.find(Computer.class, id);
  }

  public List<Computer> findAll() {
    final Query query = entityManager.createQuery("SELECT c FROM Computer c");

    return query.getResultList();
  }

}
