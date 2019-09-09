package com.example.licenta.Utils;

import com.example.licenta.Models.User;

public interface AsyncResult {
    void processFinish(Integer output);
    void processFinish(User user);
}
