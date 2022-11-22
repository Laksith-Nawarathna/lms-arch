package lk.ijse.dep9.dao;

import lk.ijse.dep9.entity.Book;
import lk.ijse.dep9.entity.IssueNote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IssueNoteDAO {

    private Connection connection;

    public IssueNoteDAO(Connection connection) {
        this.connection = connection;
    }

    public long countIssueNotes(){
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT COUNT(id) FROM issue_note");
            ResultSet rst = stm.executeQuery();
            rst.next();
            return rst.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteIssueNote(String id){
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM issue_note WHERE id = ?");
            stm.setString(1,id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsIssueNoteById(String id){
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT id FROM issue_note WHERE id = ?");
            stm.setString(1, id);
            return stm.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<IssueNote> findAllIssueNotes(){
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM issue_note");
            ResultSet rst = stm.executeQuery();
            List<IssueNote> issueNoteList = new ArrayList<>();
            while(rst.next()){
                int id = rst.getInt("id");
                Date date = rst.getDate("date");
                String memberId = rst.getString("member_id");
                issueNoteList.add(new IssueNote(id, date, memberId));
            }
            return issueNoteList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<IssueNote> findIssueNoteByID(int id){
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM issue_note WHERE id = ?");
            stm.setString(1, "id");
            ResultSet rst = stm.executeQuery();
            if(rst.next()){
                Date date = rst.getDate("date");
                String memberId = rst.getString("member_id");
                return Optional.of(new IssueNote(id, date, memberId));
            }else{
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public IssueNote saveIssueNote(IssueNote issueNote){
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO issue_note (id, date, member_id) VALUES (?, ?, ?)");
            stm.setInt(1, issueNote.getId());
            stm.setDate(2, issueNote.getDate());
            stm.setString(3, issueNote.getMemberId());
            if(stm.executeUpdate() == 1){
                return issueNote;
            }else{
                throw new SQLException("Failed to save the issue note");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public IssueNote updateIssueNote(IssueNote issueNote){
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE issue_note SET date = ?, member_id = ? WHERE id = ?");
            stm.setDate(1, issueNote.getDate());
            stm.setString(2, issueNote.getMemberId());
            stm.setInt(3, issueNote.getId());
            if(stm.executeUpdate() == 1){
                return issueNote;
            }else{
                throw new SQLException("Failed to update the issue note");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<IssueNote> findIssueNotesByQuery(String query){
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM issue_note WHERE id LIKE ? OR date LIKE ? OR issue_note.member_id LIKE ?");
            query = "%" + query + "%";
            stm.setString(1, query);
            stm.setString(2, query);
            stm.setString(3, query);
            ResultSet rst = stm.executeQuery();
            List<IssueNote> issueNotes  = new ArrayList<>();
            while(rst.next()){
                int id = rst.getInt("id");
                Date date = rst.getDate("date");
                String memberId = rst.getString("member_id");
                issueNotes.add(new IssueNote(id, date, memberId));
            }
            return issueNotes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<IssueNote> findIssueNotesByQuery(String query, int page, int size){
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM issue_note WHERE id LIKE ? OR date LIKE ? OR issue_note.member_id LIKE ? LIMIT ? OFFSET ?");
            query = "%" + query + "%";
            stm.setString(1, query);
            stm.setString(2, query);
            stm.setString(3, query);
            stm.setInt(4, page);
            stm.setInt(5, (page - 1) * size);
            ResultSet rst = stm.executeQuery();
            List<IssueNote> issueNotes  = new ArrayList<>();
            while(rst.next()){
                int id = rst.getInt("id");
                Date date = rst.getDate("date");
                String memberId = rst.getString("member_id");
                issueNotes.add(new IssueNote(id, date, memberId));
            }
            return issueNotes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<IssueNote> findAllIssueNotes(int page, int size){
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM issue_note LIMIT ? OFFSET ?");
            stm.setInt(1, page);
            stm.setInt(2, (page - 1) * size);
            ResultSet rst = stm.executeQuery();
            List<IssueNote> issueNoteList = new ArrayList<>();
            while(rst.next()){
                int id = rst.getInt("id");
                Date date = rst.getDate("date");
                String memberId = rst.getString("member_id");
                issueNoteList.add(new IssueNote(id, date, memberId));
            }
            return issueNoteList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}