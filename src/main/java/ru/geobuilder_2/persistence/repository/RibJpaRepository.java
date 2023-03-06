package ru.geobuilder_2.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import ru.geobuilder_2.Controller_DownloadFromBD;
import ru.geobuilder_2.persistence.entity.Object;
import ru.geobuilder_2.persistence.entity.Anglepair;
import ru.geobuilder_2.persistence.entity.Rib;
import ru.geobuilder_2.persistence.tools.PersistenceManager;

import java.util.List;

/**
 * Клас для запросов SQL (в котором каждый метод равен одной команде SQL)
 * @author Home
 *
 */
public class RibJpaRepository {

    /**
     * Считываем все Rib целиком
     * @return
     */
    public static List<Rib> readAllRib() {
        EntityManager em = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
            return em.createNamedQuery("Rib.findAll").getResultList();
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
     *  Записывает Rib.
     * @return Rib
     */
    public static Rib createObject(Integer tier, Integer ribLengthBD) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();

            Rib rib = new Rib();
            rib.setTier(tier);
            rib.setRibLength(ribLengthBD);

            transaction = em.getTransaction();
            transaction.begin();
            em.persist(rib);
            em.flush(); // отправляем в базу все что сделали
            transaction.commit(); // завершили транзакцию
            return rib;
        } catch (Exception e) {
            try{
                if (transaction!=null) {
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
     * Удаляет Rib из базы
     * @param
     */
    public static void deleteByEntity(Rib rib) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = PersistenceManager.INSTANCE.getEntityManager();
//			System.out.println("deleteByEntity() > angle entity is managed before merging - " + em.contains(angle));
            rib = em.merge(rib); //добавляем отделенную сущность в контекст
//			System.out.println("deleteByEntity() > angle entity is managed after merging - " + em.contains(angle));
            transaction = em.getTransaction();
            transaction.begin();
            em.remove(rib); // помечаем сущность, как удаленную из базы данных (уще не удалена)
            em.flush(); // удаляется из базы
            transaction.commit();

        } catch (Exception e) {
            try{
                if (transaction!=null) {
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
            try{
                if (transaction!=null) {
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
