package com.mitacs.customerjourney.model;

import com.mitacs.customerjourney.model.enums.Gender;
import com.mitacs.customerjourney.model.enums.HelmetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Helmet extends Product{
    private HelmetType helmetType;
    private Gender gender;
}
