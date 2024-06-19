package com.linkedin.service;

import com.linkedin.domain.dao.RequestDao;
import com.linkedin.domain.model.Request;

import java.util.ArrayList;
import java.util.UUID;

import static com.linkedin.service.BaseService.requestRepository;

public class RequestService {
    public ArrayList<RequestDao> getReceivedRequests(UUID userId) {
        return requestRepository.getReceivedRequestsOfUser(userId);
    }

    public ArrayList<RequestDao> getSentRequests(UUID userId) {
        return requestRepository.getSentRequestsOfUser(userId);
    }

    public boolean save(Request request) {
        requestRepository.save(request);
        return true;
    }

    public boolean accept(UUID request) {
        requestRepository.accept(String.valueOf(request));
        return true;
    }

    public boolean ignore(UUID request) {
        requestRepository.ignore(String.valueOf(request));
        return true;
    }

    public boolean withdraw(UUID request) {
        requestRepository.withdraw(String.valueOf(request));
        return true;
    }
}
