package com.mitacs.customerjourney.model;

import com.mitacs.customerjourney.model.enums.Material;
import com.mitacs.customerjourney.model.enums.ToolType;
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
public class Tool extends Product{
    private ToolType toolType;
    private Material material;
    private Float weight;
}
