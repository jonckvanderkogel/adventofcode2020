package org.bullet.day4;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class Passport {
    @NotNull
    private String byr;
    @NotNull
    private String iyr;
    @NotNull
    private String eyr;
    @NotNull
    private String hgt;
    @NotNull
    private String hcl;
    @NotNull
    private String ecl;
    @NotNull
    private String pid;
    // Only cid is optional
    private String cid;
}
