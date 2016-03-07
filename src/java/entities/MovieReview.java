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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joakim
 */
@Entity
@Table(name = "movie_review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieReview.findAll", query = "SELECT m FROM MovieReview m"),
    @NamedQuery(name = "MovieReview.findByReviewId", query = "SELECT m FROM MovieReview m WHERE m.reviewId = :reviewId"),
    @NamedQuery(name = "MovieReview.findByTitle", query = "SELECT m FROM MovieReview m WHERE m.title = :title"),
    @NamedQuery(name = "MovieReview.findByText", query = "SELECT m FROM MovieReview m WHERE m.text = :text")})
public class MovieReview implements Serializable {
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

    public MovieReview() {
    }

    public MovieReview(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public MovieReview(Integer reviewId, String title, String text) {
        this.reviewId = reviewId;
        this.title = title;
        this.text = text;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewId != null ? reviewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieReview)) {
            return false;
        }
        MovieReview other = (MovieReview) object;
        if ((this.reviewId == null && other.reviewId != null) || (this.reviewId != null && !this.reviewId.equals(other.reviewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MovieReview[ reviewId=" + reviewId + " ]";
    }
    
}
