package avaliacao.testes;

//GABRIEL HENRIQUE COSTA CRUZEIRO SC3037851

import avaliacao.dao.AlunoDao;
import avaliacao.modelo.Aluno;
import avaliacao.util.JPAutil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAutil.getEntityManager();
        AlunoDao school = new AlunoDao(em);
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    ==================================
                    | Escolha uma opção:             |
                    | 1. Cadastrar aluno             |
                    | 2. Excluir aluno               |
                    | 3. Alterar aluno               |
                    | 4. Buscar aluno pelo nome      |
                    | 5. Listar alunos               |
                    | 6. Finalizar                   |
                    ==================================
                    """
            );
            System.out.print("Opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> cadastrarAluno(scanner, school, em);
                case 2 -> excluirAluno(scanner, school, em);
                case 3 -> alterarAluno(scanner, school, em);
                case 4 -> findStudentByName(scanner, school);
                case 5 -> findAllStudents(school);
                case 6 -> exit = true;
                default -> System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }
        em.close();
        scanner.close();
    }

    private static void cadastrarAluno(Scanner scanner, AlunoDao school, EntityManager em) {
        System.out.print("RA do aluno: ");
        String ra = scanner.nextLine();
        System.out.print("Nome do aluno: ");
        String name = scanner.nextLine();
        System.out.print("Email do aluno: ");
        String email = scanner.nextLine();
        System.out.print("Nota 1: ");
        double grade1 = scanner.nextDouble();
        System.out.print("Nota 2: ");
        double grade2 = scanner.nextDouble();
        System.out.print("Nota 3: ");
        double grade3 = scanner.nextDouble();
        scanner.nextLine();

        em.getTransaction().begin();
        school.register(new Aluno(name, ra, email, grade1, grade2, grade3));
        em.getTransaction().commit();

        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void excluirAluno(Scanner scanner, AlunoDao school, EntityManager em) {
        System.out.print("Nome do aluno a ser excluído: ");
        String nome = scanner.nextLine();
        try {
            em.getTransaction().begin();
            school.delete(nome);
            System.out.println("Aluno excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Aluno não encontrado");
        } finally {
            em.getTransaction().commit();
        }
    }

    private static void alterarAluno(Scanner scanner, AlunoDao school, EntityManager em) {
        System.out.print("Nome do aluno a buscar: ");
        String name = scanner.nextLine();

        try {
            school.findByName(name);
            System.out.println("NOVOS DADOS: ");
            System.out.print("Novo nome do aluno: ");
            String newName = scanner.nextLine();
            System.out.print("RA do aluno a ser alterado: ");
            String ra = scanner.nextLine();
            System.out.print("Novo email do aluno: ");
            String newEmail = scanner.nextLine();
            System.out.print("Nova nota 1: ");
            double newGrade1 = scanner.nextDouble();
            System.out.print("Nova nota 2: ");
            double newGrade2 = scanner.nextDouble();
            System.out.print("Nova nota 3: ");
            double newGrade3 = scanner.nextDouble();
            scanner.nextLine();
            em.getTransaction().begin();
            school.update(name, newName, ra, newEmail, newGrade1, newGrade2, newGrade3);
            System.out.println("Aluno alterado com sucesso!");
        } catch (Exception e) {
            System.out.println("Aluno não encontrado");
        } finally {
            em.getTransaction().commit();
        }
    }

    private static void findStudentByName(Scanner scanner, AlunoDao school) {
        System.out.print("Nome do aluno a buscar: ");
        String name = scanner.nextLine();
        try {
            Aluno student = school.findByName(name);
            System.out.println("Dados do estudante:");
            System.out.printf("RA: %s%n", student.getRa());
            System.out.printf("Nome: %s%n", student.getNome());
            System.out.printf("E-mail: %s%n", student.getEmail());
            System.out.printf("Notas: %.2f - %.2f - %.2f%n", student.getNota1(), student.getNota2(), student.getNota3());
        } catch (Exception e) {
            System.out.println("Aluno não encontrado");
        }
    }

    private static void findAllStudents(AlunoDao school) {
        List<Aluno> students = school.findAll();
        if (students.isEmpty()) {
            System.out.println("Não há alunos");
        } else {
            System.out.println("Lista de alunos:");
            for (Aluno a : students) {
                System.out.println("---------------------------");
                System.out.printf("RA: %s%n", a.getRa());
                System.out.printf("Nome: %s%n", a.getNome());
                System.out.printf("E-mail: %s%n", a.getEmail());
                System.out.printf("Notas: %.2f - %.2f - %.2f%n", a.getNota1(), a.getNota2(), a.getNota3());
                System.out.printf("Média: %.2f%n", a.getMedia());
                System.out.printf("Situação: %s%n", a.getSituation());
            }
        }
    }
}
