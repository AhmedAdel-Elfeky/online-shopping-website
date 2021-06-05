package souq.com;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author m_elieba
 */
public class ProductComment {
    private String commentId = "";
    private String Comment;
    private String timer = "";
    private int userId;
    private int productId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }
    public ProductComment (){
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getTimer() {
        return timer;
    }    
}
