/*
 * Copyright 2009-2013 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.orangesignal.csv.filters;

import java.util.Collection;
import java.util.List;

/**
 * 指定された項目名に対応する区切り文字形式データの値が判定基準値群のいずれとも等しくないかどうかでフィルタを適用する区切り文字形式データフィルタの実装です。
 * 
 * @author Koji Sugisawa
 * @since 1.2.3
 */
public class ColumnNameNotInExpression extends ColumnNameCriteriasExpression {

	/**
	 * コンストラクタです。
	 * 
	 * @param name 項目名
	 * @param criterias 判定基準値群
	 * @throws IllegalArgumentException パラメータが <code>null</code> の場合
	 */
	protected ColumnNameNotInExpression(final String name, final Collection<String> criterias) {
		super(name, criterias);
	}

	/**
	 * コンストラクタです。
	 * 
	 * @param name 項目名
	 * @param criterias 判定基準値群
	 * @param ignoreCase 大文字と小文字を区別するかどうか
	 * @throws IllegalArgumentException パラメータが <code>null</code> の場合
	 */
	protected ColumnNameNotInExpression(final String name, final Collection<String> criterias, final boolean ignoreCase) {
		super(name, criterias, ignoreCase);
	}

	/**
	 * コンストラクタです。
	 * 
	 * @param name 項目名
	 * @param criterias 判定基準値群
	 * @throws IllegalArgumentException パラメータが <code>null</code> の場合
	 */
	protected ColumnNameNotInExpression(final String name, final String... criterias) {
		super(name, criterias);
	}

	/**
	 * コンストラクタです。
	 * 
	 * @param name 項目名
	 * @param criterias 判定基準値群
	 * @param ignoreCase 大文字と小文字を区別するかどうか
	 * @throws IllegalArgumentException パラメータが <code>null</code> の場合
	 */
	protected ColumnNameNotInExpression(final String name, final String[] criterias, final boolean ignoreCase) {
		super(name, criterias, ignoreCase);
	}

	@Override
	public boolean accept(final List<String> header, final List<String> values) {
		final int position = header.indexOf(name);
		if (position == -1) {
			throw new IllegalArgumentException(String.format("Invalid column name %s", name));
		}
		return CsvExpressionUtils.notIn(values, position, criterias, ignoreCase);
	}

}
