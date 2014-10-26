package com.reiterweg.ytg.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.reiterweg.ytg.domain.Prime;

@SuppressWarnings("serial")
public class PrimeFactorsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
		String[] numbers = req.getParameterValues("number");
		Prime<?>[] primes = null;

		if (numbers != null) {
			primes = new Prime[numbers.length];

			for (int i = 0; i < primes.length; i++) {
				primes[i] = value(numbers[i]);
			}
		}

		resp.setContentType("application/json");
		resp.getWriter().println(objectMapper.writeValueAsString((primes.length == 1) ? primes[0] : primes));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Prime value(String parameter) {
		List<Integer> decomposition = null;
		Prime prime = null;
		Integer number = null;

		try {
			number = Integer.parseInt(parameter);

			if (number <= 1e6) {
				decomposition = decompositeNumber(number);

				prime = new Prime<Integer>();
				prime.setNumber(number);
				prime.setDecomposition(decomposition.toArray(new Integer[decomposition.size()]));
			} else {
				prime = new Prime<String>();
				prime.setNumber(parameter);
				prime.setError("too big number (>1e6)");
			}
		} catch (Exception e) {
			prime = new Prime<String>();
			prime.setNumber(parameter);
			prime.setError("not a number");
		}

		return prime;
	}

	private List<Integer> decompositeNumber(Integer number) {
		List<Integer> decomposition = new ArrayList<Integer>();
		Integer eval = number;
		Integer count = null;

		for (int factor = 2; factor <= number; factor++) {
			count = 0;

			while ((eval % factor) == 0) {
				eval /= factor;
				count++;
			}

			if (count == 0) {
				continue;
			}

			for (int i = 0; i < count; i++) {
				decomposition.add(factor);
			}
		}

		return decomposition;
	}

}
