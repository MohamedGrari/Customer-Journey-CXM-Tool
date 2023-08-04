package com.mitacs.customerjourney.model;

import com.mitacs.customerjourney.model.enums.ComponentType;
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
public class Component extends Product{
    private ComponentType componentType;
}
