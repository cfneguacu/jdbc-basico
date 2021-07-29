package part3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public List<Aluno> list(){
        List<Aluno> alunos = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getConnection()){
            PreparedStatement prst = conn.prepareStatement("SELECT * FROM aluno");

           // PreparedStatement stmt = conn.preparedStatement(sql);

            ResultSet rs = prst.executeQuery();

            while(rs.next()){
               Aluno aluno = new Aluno(
                       rs.getInt("id"),
                       rs.getString("nome"),
                       rs.getInt("idade"),
                       rs.getString("estado")
               );
               alunos.add(aluno);
            }
        }catch(SQLException e){
            System.out.println("Listagem de alunos FALHOU");
            e.printStackTrace();
        }
        return alunos;
    }

    public Aluno getById(int id){
        Aluno aluno = new Aluno();

        try(Connection conn = ConnectionFactory.getConnection()){
            String sql ="SELECT * FROM aluno WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));
            }
        }catch (SQLException e){
            System.out.print("Listagem de Alunos FALHOU!!");
            e.printStackTrace();
        }

        return aluno;
    }

    public void create(Aluno aluno){
        try(Connection conn = ConnectionFactory.getConnection()){
            String sql ="INSERT INTO aluno(nome, idade, estado) VALUES(?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,aluno.getNome());
            stmt.setInt(2,aluno.getIdade());
            stmt.setString(3,aluno.getEstado());

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Inserção BEM SUCEDIDA! Foi Adcionada "+rowsAffected+ " linha");
        }catch(SQLException e){
            System.out.println("Inserção FALHOU!");
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM aluno WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Delete BEM SUCEDIDA! Foi deletada " + rowsAffected + " linha");

        } catch (SQLException e) {
            System.out.println("Delete FALHOU!");
            e.printStackTrace();
        }
    }

    public void update(Aluno aluno){
        try(Connection conn = ConnectionFactory.getConnection()){
            String sql = "UPDATE aluno SET nome = ?, idade = ? , estado = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,aluno.getNome());
            stmt.setInt(2,aluno.getIdade());
            stmt.setString(3,aluno.getEstado());
            stmt.setInt(4,aluno.getId());

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Atualização BEM SUCEDIDA! Foi atualizada: "+rowsAffected+" linha");


        }catch(SQLException e){
            System.out.println("Atualização FALHOU!");
            e.printStackTrace();
        }
    }
}
