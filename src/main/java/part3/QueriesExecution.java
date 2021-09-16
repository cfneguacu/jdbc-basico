package part3;

import java.util.Arrays;
import java.util.List;

public class QueriesExecution {

    public static void main(String[] args){
        AlunoDAO alunoDAO = new AlunoDAO();
        
        Aluno alunoParaInsercao = new Aluno(
               "Matheus",
               43,
             "SP"
        );

        alunoDAO.create(alunoParaInsercao);
        
        Aluno alunoParaConsulta = alunoDAO.getById(1);
        System.out.println(alunoParaConsulta);
        
        List<Aluno> alunos = alunoDAO.list();
        alunos.stream().forEach(System.out::println);
     
        Aluno alunoParaAtualizar = alunoDAO.getById(1);
        alunoParaAtualizar.setNome("Joaquim");
        alunoParaAtualizar.setIdade(18);
        alunoParaAtualizar.setEstado("RS");

        alunoDAO.update(alunoParaAtualizar);
        
        Aluno alunoParaConsulta = alunoDAO.getById(1);
        System.out.println(alunoParaConsulta);
        
        alunoDAO.delete(1);
    }
}
