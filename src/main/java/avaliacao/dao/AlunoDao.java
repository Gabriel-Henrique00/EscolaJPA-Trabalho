package avaliacao.dao;

import avaliacao.modelo.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.List;

public class AlunoDao {
    private EntityManager em;
    public AlunoDao(EntityManager em) {
        this.em = em;
    }
    public void register(Aluno aluno) {
        this.em.persist(aluno);
    }

    public void delete(String name) throws NoResultException{
        int deleteCount = em.createQuery("DELETE FROM Aluno a WHERE a.nome = :n")
                .setParameter("n", name)
                .executeUpdate();
        if (deleteCount == 0) {
            throw new NoResultException("Aluno não encontrado");
        }
    }

    public void update(String oldName, String newName, String newRa, String newEmail, double newGrade1, double newGrade2, double newGrade3) throws NoResultException {
        Aluno aluno = em.createQuery("SELECT a FROM Aluno a WHERE a.nome = :n", Aluno.class)
                .setParameter("n", oldName)
                .getSingleResult();
        if (aluno != null) {
            aluno.setNome(newName);
            aluno.setRa(newRa);
            aluno.setEmail(newEmail);
            aluno.setNota1(newGrade1);
            aluno.setNota2(newGrade2);
            aluno.setNota3(newGrade3);
        } else {
            throw new NoResultException("Aluno não encontrado");
        }
    }

    public List<Aluno> findAll() {
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }

    public Aluno findByName(String name) throws NoResultException {
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = :n";
        return em.createQuery(jpql, Aluno.class)
                .setParameter("n", name)
                .getSingleResult();
    }
}
