package com.msa.ratingsservice.service;

import com.msa.ratingsservice.exception.NotFoundException;
import com.msa.ratingsservice.model.Rating;
import com.msa.ratingsservice.repo.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public Rating updateAverage(String name, double stars){

        Rating rating = ratingRepository.findByName(name);

        if (rating == null){
            rating = new Rating();
            rating.setName(name);
            rating.setAverageRating(stars);
            rating.setCount(1);
        } else {

            int count = rating.getCount();
            double newAverage = (rating.getAverageRating() * count + stars) / (count + 1);

            rating.setAverageRating(newAverage);
            rating.setCount(++count);
        }
        return ratingRepository.save(rating);
    }

    public Rating fetchrating(String name){
        Rating rating = ratingRepository.findByName(name);

        if (rating == null){
            throw new NotFoundException("Movie not found with name: "+ name);
        }
        return rating;
    }

}
