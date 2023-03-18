package ru.geobuilder_2.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import javafx.collections.ObservableList;
import ru.geobuilder_2.parser.InputData;
import ru.geobuilder_2.persistence.entity.Instance;
import ru.geobuilder_2.persistence.entity.Object;
import ru.geobuilder_2.persistence.entity.Rib;
import ru.geobuilder_2.persistence.tools.PersistenceManager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class InstanceJpaRepository {

    public static Instance readInstance(Long id) {
        EntityManager em = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
            return em.find(Instance.class, id);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Считываем объекты Instance
    public static List<Instance> readAllInstance() {
        EntityManager em = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
            return em.createNamedQuery("Instance.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Записывает entity Instance.
     *
     * @return
     */
    public static Instance createInstance(String typeOfWork, String numberBasisOfWork,
                                          String author, Timestamp photoDateColumn, Timestamp creationDate) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();

            Instance instance = new Instance();
            instance.setTypeOfWork(typeOfWork);
            instance.setNumberBasisOfWork(numberBasisOfWork);
            instance.setAuthor(author);

            transaction = em.getTransaction();
            transaction.begin();
            em.persist(instance);
            em.flush(); // отправляем в базу все что сделали
            transaction.commit(); // завершили транзакцию
            return instance;
        } catch (Exception e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace(System.out);
            }
            e.printStackTrace(System.out);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Записывает entity Instance.
     *
     * @return
     */
    public static Instance createInstanceForObjectWithData(Long idObject, String typeOfWork, String numberBasisOfWork,
                                                           String author, String path, int quantity, ObservableList<ru.geobuilder_2.Rib> ribss) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();

            Instance instance = new Instance();
            instance.setTypeOfWork(typeOfWork);
            instance.setNumberBasisOfWork(numberBasisOfWork);
            instance.setAuthor(author);

            for (int i=0; i<quantity; i++) {
                Rib rib = new Rib();
                rib.setTier((Integer) ribss.get(i).getTier());
                rib.setRibLength(Integer.valueOf(ribss.get(i).getRibLength()));
                instance.addRib(rib);
                //em.persist(rib);
            }

            try {
                InputData inputData = new InputData();
                inputData.readInputData(path);
                inputData.getPoints().forEach(p -> instance.addPoint(p));
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }

            if(instance.getPoints().isEmpty() || instance.getRibs().isEmpty()){

            } else {
                // Получаем объект по id
                ObjectJpaRepository objectJpaRepository = new ObjectJpaRepository();
                Object obj = objectJpaRepository.read(idObject);
                obj.addInstance(instance);

                transaction = em.getTransaction();
                transaction.begin();
                em.merge(obj);
                transaction.commit(); // завершили транзакцию
                return instance;
            }
            return null;
        } catch (Exception e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace(System.out);
            }
            e.printStackTrace(System.out);
            return null;
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
}
