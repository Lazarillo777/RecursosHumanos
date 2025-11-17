package com.example.app.dao;

import com.example.app.model.FamilyMember;

import java.util.List;

public interface FamilyMemberDao {
    FamilyMember save(FamilyMember member);

    FamilyMember update(FamilyMember member);

    boolean deleteById(long id);

    FamilyMember findById(long id);

    List<FamilyMember> findAllByEmployeeId(long employeeId);
}
