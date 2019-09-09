package com.example.licenta.Project;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.licenta.Dao.UserDao;
import com.example.licenta.Database.UserDatabase;
import com.example.licenta.Models.User;
import com.example.licenta.Utils.AsyncResult;


public class UserRepository {

    private UserDao mUserDao;

    public AsyncResult delegate = null;

    UserRepository(Application application) {
        UserDatabase db = UserDatabase.getDatabase(application);
        mUserDao = db.userDao();
    }

    private static class MyTaskParams {
        String email;
        String password;

        MyTaskParams(String email, String password) {
            this.email = email;
            this.password = password;
        }

    }

    void getUser(int id) { new UserRepository.getUserDataAsyncTask(mUserDao, delegate).execute(id); }

    void insert(User user) {
        new UserRepository.insertAsyncTask(mUserDao).execute(user);
    }

    void update(User user) {
        new UserRepository.updateAsyncTask(mUserDao).execute(user);
    }

    void find(String email, String password) {
        MyTaskParams myTaskParams = new MyTaskParams(email, password);
        new UserRepository.findAsyncTask(mUserDao, delegate).execute(myTaskParams);
    }

    void delete(User user) { new UserRepository.deleteAsyncTask(mUserDao).execute(user); }

    private static class getUserDataAsyncTask extends AsyncTask<Integer, Void, User> {

        private UserDao mAsyncTaskDao;
        public AsyncResult delegate = null;

        getUserDataAsyncTask(UserDao mAsyncTaskDao, AsyncResult asyncResult) {
            this.mAsyncTaskDao = mAsyncTaskDao;
            this.delegate = asyncResult;
        }

        @Override
        protected User doInBackground(Integer ... id) {
            return mAsyncTaskDao.getUser(id[0]);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
        }
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... users) {
            mAsyncTaskDao.insert(users[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        updateAsyncTask(UserDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mAsyncTaskDao.update(users[0]);
            return null;
        }
    }

    private static class findAsyncTask extends AsyncTask<MyTaskParams, Void, Integer> {

        private UserDao mAsyncTaskDao;
        public AsyncResult delegate = null;

        public findAsyncTask(UserDao mAsyncTaskDao, AsyncResult asyncResult) {
            this.mAsyncTaskDao = mAsyncTaskDao;
            this.delegate = asyncResult;
        }

        @Override
        protected Integer doInBackground(MyTaskParams... myTaskParams) {
            return mAsyncTaskDao.find(myTaskParams[0].email, myTaskParams[0].password);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            delegate.processFinish(integer);
        }
    }

    private static class deleteAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        deleteAsyncTask(UserDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mAsyncTaskDao.delete(users[0]);
            return null;
        }
    }
}
