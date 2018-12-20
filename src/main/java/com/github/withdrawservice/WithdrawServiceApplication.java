package com.github.withdrawservice;

import com.github.withdrawservice.entity.BankAccountEntity;
import com.github.withdrawservice.entity.WalletEntity;
import com.github.withdrawservice.repository.BankAccountRepository;
import com.github.withdrawservice.repository.WalletRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@SpringBootApplication
public class WithdrawServiceApplication {

    @Bean
    CommandLineRunner commandLineRunner(WalletRepository wr, BankAccountRepository bar) {
        return strings -> {
            wr.save(new WalletEntity(1L, 1L, 50L));
            bar.save(new BankAccountEntity(1L, 1L, 0));
        };
    }

    @ControllerAdvice
    class ExceptionControllerAdvice {


        @ResponseBody
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler({BadRequestException.class, EntityNotFoundException.class, ConstraintViolationException.class})
        public ValidationModel processValidationError(final RuntimeException ex) {
            return new ValidationModel(ex.getMessage());
        }

        private class ValidationModel {
            private String message;

            private ValidationModel(String message) {
                this.message = message;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
    }


    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(WithdrawServiceApplication.class, args);
    }
}

