/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joakim
 */
@Entity
@Table(name = "game_review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GameReview.findAll", query = "SELECT g FROM GameReview g"),
    @NamedQuery(name = "GameReview.findByReviewId", query = "SELECT g FROM GameReview g WHERE g.reviewId = :reviewId"),
    @NamedQuery(name = "GameReview.findByTitle", query = "SELECT g FROM GameReview g WHERE g.title = :title"),
    @NamedQuery(name = "GameReview.findByText", query = "SELECT g FROM GameReview g WHERE g.text = :text"),
    @NamedQuery(name = "GameReview.findByRecommended", query = "SELECT g FROM GameReview g WHERE g.recommended = :recommended")})
public class GameReview implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "review_id")
    private Integer reviewId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recommended")
    private int recommended;
    @JoinColumn(name = "review_id", referencedColumnName = "game_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Game game;

    public GameReview() {
    }

    public GameReview(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public GameReview(Integer reviewId, String title, String text, int recommended) {
        this.reviewId = reviewId;
        this.title = title;
        this.text = text;
        this.recommended = recommended;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRecommended() {
        return recommended;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewId != null ? reviewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GameReview)) {
            return false;
        }
        GameReview other = (GameReview) object;
        if ((this.reviewId == null && other.reviewId != null) || (this.reviewId != null && !this.reviewId.equals(other.reviewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GameReview[ reviewId=" + reviewId + " ]";
    }
    
}
