package ru.geobuilder_2.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import ru.geobuilder_2.Controller_DownloadFromBD;
import ru.geobuilder_2.persistence.entity.Object;
import ru.geobuilder_2.persistence.entity.Anglepair;
import ru.geobuilder_2.persistence.tools.PersistenceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас для запросов SQL (в котором каждый метод равен одной команде SQL)
 *
 * @author Home
 */
public class ObjectJpaRepository {
    // Считываем entity по ID. В EntityManager заложен код поиска ID
    public static Object read(Long id) {
        EntityManager em = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
            return em.find(Object.class, id);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }

//		EntityTransaction transaction = em.getTransaction();
//		transaction.begin();
//		
//		transaction.commit(); // em.flush()
//		em.close();
    }

    public static List<Object> readByCode(String code) {
        EntityManager em = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
            return em.createNamedQuery("Object.findByCode").setParameter("code", "%" + code + "%").getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static List<Object> readByOperator(String operator) {
        EntityManager em = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
            return em.createNamedQuery("Object.findByOperator").setParameter("operator", operator).getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Считываем объекты
    public static List<Object> readAllObject() {
        EntityManager em = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
            return em.createNamedQuery("Object.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }

//			EntityTransaction transaction = em.getTransaction();
//			transaction.begin();
//			
//			transaction.commit(); // em.flush()
//			em.close();
    }

    /**
     * Записывает entity. Делается через транзакцию.
     *
     * @return
     */
    public static Object createObject(String numberObject, String operatorObject, String addressObject) throws Exception {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();

            Object object = new Object();
            object.setNumber(numberObject);
            object.setOperator(operatorObject);
            object.setAddress(addressObject);

            transaction = em.getTransaction();
            transaction.begin();
            em.persist(object);
            em.flush(); // отправляем в базу все что сделали
            transaction.commit(); // завершили транзакцию
            return object;
        } catch (Exception e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace(System.out);
            }
            e.printStackTrace(System.out);
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
///**
// * Создаем сразу партию значений Object. Список значений типа <Object>
// * @param quantity
// */
//	public static void createBatch(int quantity) {
//		EntityManager em = null;
//		EntityTransaction transaction = null;
//		try {
//			em = PersistenceManager.INSTANCE.getEntityManager();
//			transaction = em.getTransaction();
//			transaction.begin();
//			for (int i=0; i<quantity; i++) {
//				Object object = new Object();
//				object.setNumber();
//				object.setOperator();
//				object.setAddress();
//				em.persist(object);
//			}
//			em.flush();
//			transaction.commit();
//
//		} catch (Exception e) {
//			try{
//				if (transaction!=null) {
//					transaction.rollback();
//				}
//			} catch (Exception e1) {
//				e1.printStackTrace(System.out);
//			}
//			e.printStackTrace(System.out);
//		} finally {
//			if (em != null) {
//				em.close();
//			}
//		}
//	}

    /**
     * Удаляет сущность из базы
     *
     * @param object
     */
    public static void deleteByEntity(Object object) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
//			System.out.println("deleteByEntity() > angle entity is managed before merging - " + em.contains(angle));
            object = em.merge(object); //добавляем отделенную сущность в контекст
//			System.out.println("deleteByEntity() > angle entity is managed after merging - " + em.contains(angle));
            transaction = em.getTransaction();
            transaction.begin();
            em.remove(object); // помечаем сущность, как удаленную из базы данных (уще не удалена)
            em.flush(); // удаляется из базы
            transaction.commit();

        } catch (Exception e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace(System.out);
            }
            e.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Удалить сущность с помощью запроса SQL(расширенного JPA. JPQL)
     *
     * @param id
     */
    public static void deleteByIdWithJPQL(Long id) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            Query q = em.createQuery("DELETE FROM Object o WHERE o.id = :id");
            q.setParameter("id", id);
            q.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace(System.out);
            }
            e.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static List<Object> findByParameters(String code, String operator) {
        List<Object> oL = new ArrayList<Object>();
        String qF = "SELECT o FROM Object o WHERE ";
        EntityManager em = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
            if (code != null && !code.isEmpty()) {
                qF += "o.number LIKE :code ";
                if (operator != null && !operator.isEmpty()) {
                    qF += "AND ";
                }
            }
            if (operator != null && !operator.isEmpty()) {
                qF += "o.operator = :operator";
            }
            Query q = em.createQuery(qF);
            if (code != null && !code.isEmpty()) {
                q.setParameter("code", "%" + code + "%");
            }
            if (operator != null && !operator.isEmpty()) {
                q.setParameter("operator", operator);
            }
            oL = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
			throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return oL;
    }

    /**
     * Метод удаления с помощью операции из EntityManager
     *
     * @param id
     */
    public static void deleteByIdViaPersistenceContext(Long id) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
            Object object = em.find(Object.class, id);
            transaction = em.getTransaction();
            transaction.begin();
            em.remove(object);
            em.flush();
            transaction.commit();

        } catch (Exception e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace(System.out);
            }
            e.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Удаляем по запросу, обозначенному в Angel
     *
     * @param id
     */
    public static void deleteByIdViaNamedQuery(Long id) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.createNamedQuery("Object.deleteById").setParameter("id", id).executeUpdate(); //отсутствует в Object (пока)
            em.flush();
            transaction.commit();

        } catch (Exception e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace(System.out);
            }
            e.printStackTrace(System.out);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void main(String[] args) {
//		System.out.println(ObjectJpaRepository.readByCode("55" + "\n"));
//		System.out.println(ObjectJpaRepository.readByOperator("ПАО МТС"));
        System.out.println(ObjectJpaRepository.findByParameters("1", "ПАО Мегафон"));
    }

}
