-- Courses

CREATE TABLE `courses` (
  `id` int(11) NOT NULL,
  `title_gr` VARCHAR(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title_en` VARCHAR(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `semester` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `teachers_gr` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `teachers_en` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=UTF8_UNICODE_CI;

ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`);
  
ALTER TABLE `courses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

-- Teachers

CREATE TABLE `teachers` (
  `id` int(11) NOT NULL,
  `name_gr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `personal_site` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_gr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_en` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `teachers`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `teachers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

-- Communities

CREATE TABLE `communities` (
  `id` int(11) NOT NULL,
  `title_gr` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title_en` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `communities`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `communities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

-- Offers

CREATE TABLE `offers` (
  `id` int(11) NOT NULL,
  `title_gr` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title_en` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_gr` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_en` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `offers`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `offers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

-- Maps

CREATE TABLE `maps` (
  `id` int(11) NOT NULL,
  `latitude` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `longitude` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title_gr` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title_en` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_gr` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_en` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `maps`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `maps`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

-- Official Services

CREATE TABLE `official_services` (
  `id` int(11) NOT NULL,
  `title_gr` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title_en` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_gr` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_en` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `official_services`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `official_services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

-- Unofficial Services

CREATE TABLE `unofficial_services` (
  `id` int(11) NOT NULL,
  `title_gr` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title_en` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_gr` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_en` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creator_gr` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creator_en` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `unofficial_services`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `unofficial_services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

-- Useful Website

CREATE TABLE `useful_websites` (
  `id` int(11) NOT NULL,
  `title_gr` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title_en` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_gr` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_en` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `useful_websites`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `useful_websites`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
