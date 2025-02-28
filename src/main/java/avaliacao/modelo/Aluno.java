package avaliacao.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String ra;
    private String email;
    private double nota1;
    private double nota2;
    private double nota3;

    public Aluno(String nome, String ra, String email, double nota1, double nota2, double nota3) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public Aluno() {}

    public String getNome() { return nome; }
    public String getRa() { return ra; }
    public String getEmail() { return email; }
    public double getNota1() { return nota1; }
    public double getNota2() { return nota2; }
    public double getNota3() { return nota3; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setRa(String ra) { this.ra = ra; }
    public void setEmail(String email) { this.email = email; }
    public void setNota1(double nota1) { this.nota1 = nota1; }
    public void setNota2(double nota2) { this.nota2 = nota2; }
    public void setNota3(double nota3) { this.nota3 = nota3; }

    public double getMedia() {
        return (nota1 + nota2 + nota3) / 3;
    }

    @Override
    public String toString() {
        return String.format(
                "Aluno{id=%d, nome='%s', ra='%s', email='%s', nota1=%.2f, nota2=%.2f, nota3=%.2f}",
                id, nome, ra, email, nota1, nota2, nota3
        );
    }

    public String getSituation() {
        double media = getMedia();
        if (media < 4) {
            return "Reprovado";
        } else if (media < 6) {
            return "Recuperação";
        } else {
            return "Aprovado";
        }
    }
}
