-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2023 at 06:56 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restaurantmanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_categories`
--

CREATE TABLE `tbl_categories` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delete_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_categories`
--

INSERT INTO `tbl_categories` (`id`, `name`, `description`, `image`, `status_id`, `created_at`, `updated_at`, `delete_at`) VALUES
(1, 'Sports Equipment', 'Get your game on with the latest sports equipment!', 'https://picsum.photos/200/300.jpg', 10, '2023-06-19 16:31:09', '2023-06-19 16:31:09', NULL),
(2, 'Outdoor Gear', 'Explore the great outdoors with top-of-the-line gear!', 'https://picsum.photos/200/300.jpg', 11, '2023-06-19 16:31:09', '2023-06-19 16:31:09', NULL),
(3, 'Home Decor', 'Make your house a home with stylish decor!', 'https://picsum.photos/200/300.jpg', 10, '2023-06-19 16:31:09', '2023-06-19 16:31:09', NULL),
(4, 'Gourmet Food', 'Indulge in the finest gourmet food and drink!', 'https://picsum.photos/200/300.jpg', 11, '2023-06-19 16:31:09', '2023-06-19 16:31:09', NULL),
(5, 'Fashion Accessories', 'Step up your style game with the latest fashion accessories!', 'https://picsum.photos/200/300.jpg', 10, '2023-06-19 16:31:09', '2023-06-19 16:31:09', NULL),
(6, 'Pet Supplies', 'Keep your furry friends happy and healthy with top-quality pet supplies!', 'https://picsum.photos/200/300.jpg', 11, '2023-06-19 16:31:09', '2023-06-19 16:31:09', NULL),
(7, 'Health and Beauty', 'Look and feel your best with the latest health and beauty products!', 'https://picsum.photos/200/300.jpg', 10, '2023-06-19 16:31:09', '2023-06-19 16:31:09', NULL),
(8, 'Electronics', 'Stay connected with the latest electronics and gadgets!', 'https://picsum.photos/200/300.jpg', 11, '2023-06-19 16:31:09', '2023-06-19 16:31:09', NULL),
(9, 'Toys and Games', 'Have fun and let your imagination run wild with the latest toys and games!', 'https://picsum.photos/200/300.jpg', 10, '2023-06-19 16:31:09', '2023-06-19 16:31:09', NULL),
(10, 'Books and Media', 'Expand your mind and enrich your life with the latest books and media!', 'https://picsum.photos/200/300.jpg', 11, '2023-06-19 16:31:09', '2023-06-19 16:31:09', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_chats`
--

CREATE TABLE `tbl_chats` (
  `id` int(11) NOT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delete_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_chats`
--

INSERT INTO `tbl_chats` (`id`, `admin_id`, `user_id`, `content`, `image`, `created_at`, `updated_at`, `delete_at`) VALUES
(1, 62, 61, 'Hi there!', 'https://random.com/image1.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(2, 62, 64, 'How can I help you?', 'https://random.com/image2.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(3, 62, 67, 'What brings you here?', 'https://random.com/image3.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(4, 65, 61, 'Do you have any questions?', 'https://random.com/image4.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(5, 65, 64, 'Im here to assist you.', 'https://random.com/image5.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(6, 65, 67, 'Let me know if you need anything.', 'https://random.com/image6.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(7, 68, 61, 'Welcome!', 'https://random.com/image7.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(8, 68, 64, 'How can I assist you today?', 'https://random.com/image8.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(9, 68, 67, 'What can I help you with?', 'https://random.com/image9.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(10, 71, 61, 'How can I assist you?', 'https://random.com/image10.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(11, 71, 64, 'What brings you here?', 'https://random.com/image11.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(12, 71, 67, 'Let me know if you need any assistance.', 'https://random.com/image12.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(13, 74, 61, 'Do you have any questions?', 'https://random.com/image13.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(14, 74, 64, 'How can I help you today?', 'https://random.com/image14.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(15, 74, 67, 'What can I assist you with?', 'https://random.com/image15.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(16, 77, 61, 'Hi there!', 'https://random.com/image16.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(17, 77, 64, 'What brings you here?', 'https://random.com/image17.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(18, 77, 67, 'Let me know if you need any assistance.', 'https://random.com/image18.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(19, 80, 61, 'How can I assist you today?', 'https://random.com/image19.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL),
(20, 80, 64, 'Do you have any questions?', 'https://random.com/image20.jpeg', '2023-06-19 16:52:44', '2023-06-19 16:52:44', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_postdetails`
--

CREATE TABLE `tbl_postdetails` (
  `id` int(11) NOT NULL,
  `post_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delete_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_postdetails`
--

INSERT INTO `tbl_postdetails` (`id`, `post_id`, `product_id`, `created_at`, `updated_at`, `delete_at`) VALUES
(1, 1, 1, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(2, 1, 2, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(3, 3, 3, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(4, 3, 4, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(5, 3, 5, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(6, 4, 6, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(7, 5, 7, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(8, 6, 8, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(9, 6, 9, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(10, 7, 10, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(11, 7, 11, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(12, 7, 12, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(13, 7, 13, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(14, 8, 14, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(15, 8, 15, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(16, 9, 16, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(17, 10, 17, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(18, 10, 18, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(19, 10, 19, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(20, 11, 20, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(21, 12, 1, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(22, 12, 2, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(23, 12, 3, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(24, 12, 4, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(25, 2, 5, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(26, 2, 6, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(27, 11, 7, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(28, 1, 8, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(29, 3, 9, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL),
(30, 5, 10, '2023-06-19 16:48:04', '2023-06-19 16:48:04', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_posts`
--

CREATE TABLE `tbl_posts` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `restaurant_id` int(11) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delete_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_posts`
--

INSERT INTO `tbl_posts` (`id`, `name`, `description`, `content`, `image`, `category_id`, `restaurant_id`, `status_id`, `created_at`, `updated_at`, `delete_at`) VALUES
(1, 'Post 1', 'This is the description for post 1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fringilla, sapien ac malesuada egestas, mauris metus tristique diam, vel aliquet libero ex quis nulla. Morbi blandit, dui vel semper faucibus, quam ipsum tempor elit, et blandit ligula risus sed turpis. Sed ut felis risus. Sed justo neque, fermentum vel est vel, ultricies tincidunt elit. Donec ac metus at enim ultrices sagittis. Sed sit amet semper magna.', 'https://picsum.photos/200/300.jpg', 1, 63, 8, '2023-06-19 16:43:40', '2023-06-19 16:43:40', NULL),
(2, 'Post 2', 'This is the description for post 2', 'Suspendisse potenti. Nulla facilisi. Suspendisse potenti. Nulla facilisi. Quisque non dolor in elit accumsan lobortis. Nunc quis congue risus. Nullam nec risus vel velit faucibus suscipit. Morbi nec lectus nec orci porttitor viverra in sit amet lectus. Sed sagittis, ipsum eget sollicitudin mattis, massa sapien ultrices diam, id hendrerit nibh augue vel risus. Aenean sit amet mauris purus.', 'https://picsum.photos/200/300.jpg', 2, 66, 9, '2023-06-19 16:43:40', '2023-06-19 16:43:40', NULL),
(3, 'Post 3', 'This is the description for post 3', 'Vestibulum tincidunt, arcu vel pharetra accumsan, quam orci hendrerit urna, nec malesuada velit quam sed nisi. Nulla facilisi. Phasellus euismod posuere lobortis. Donec vel lorem eget nibh consectetur blandit. Pellentesque sed finibus velit. Vestibulum eu semper tellus, a aliquam ante. Sed ut est vel ipsum elementum imperdiet. Duis vel aliquam turpis. Donec pharetra nisl at tempor varius.', 'https://picsum.photos/200/300.jpg', 3, 69, 8, '2023-06-19 16:43:40', '2023-06-19 16:43:40', NULL),
(4, 'Post 4', 'This is the description for post 4', 'Vestibulum dignissim lacinia fermentum. Ut bibendum placerat lacus, a gravida erat iaculis eget. Maecenas vestibulum, augue eu placerat consequat, purus tortor malesuada risus, sed accumsan mauris eros ut urna. Donec sed sapien eu mauris luctus gravida sit amet quis dolor. Curabitur eget interdum ipsum. Nam euismod hendrerit turpis a auctor. Curabitur blandit ligula vitae hendrerit bibendum. Sed euismod arcu vel massa efficitur, ac tincidunt velit molestie. ', 'https://picsum.photos/200/300.jpg', 4, 72, 9, '2023-06-19 16:43:40', '2023-06-19 16:43:40', NULL),
(5, 'Post 5', 'This is the description for post 5', 'Praesent eu magna euismod, condimentum mi eget, faucibus nulla. Suspendisse ut sapien in erat placerat iaculis. In hac habitasse platea dictumst. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec accumsan, felis sit amet posuere maximus, ligula arcu bibendum nibh, sit amet egestas libero elit id turpis. Proin sit amet libero vestibulum, posuere orci vel, bibendum quam.', 'https://picsum.photos/200/300.jpg', 5, 75, 8, '2023-06-19 16:43:40', '2023-06-19 16:43:40', NULL),
(6, 'Post 6', 'This is the description for post 6', 'Morbi auctor euismod enim, non ultrices augue bibendum id. Donec bibendum, elit in fringilla ultrices, ex mi scelerisque nulla, id dictum velit sapien sit amet mauris. Suspendisse potenti. Morbi dignissim, nisi id molestie dapibus, nisl eros dictum nunc, ut pulvinar libero sapien vel eros. Praesent ac quam nec enim posuere suscipit eu ut velit. Integer auctor at ligula vel dictum. Fusce sit amet quam sed odio ullamcorper feugiat. ', 'https://picsum.photos/200/300.jpg', 6, 78, 9, '2023-06-19 16:43:40', '2023-06-19 16:43:40', NULL),
(7, 'Post 7', 'This is the description for post7', 'Nulla ut sagittis velit, ac hendrerit enim. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed vitae erat eget nibh commodo lacinia. Vestibulum vel cursus quam. Sed auctor, quam eu lacinia ornare, quam tellus bibendum diam, sit amet bibendum enim elit a ipsum. Maecenas vel ex eros. Aliquam erat volutpat. ', 'https://picsum.photos/200/300.jpg', 7, 63, 8, '2023-06-19 16:43:40', '2023-06-19 16:43:40', NULL),
(8, 'Post 8', 'This is the description for post 8', 'Donec feugiat blandit felis, vel bibendum justo cursus in. Curabitur ultricies lobortis mi, eu consectetur sapien lacinia sit amet. Sed eget lacinia nisi. Duis eu luctus nunc. In ut blandit quam. Pellentesque sed elit eget nulla iaculis sagittis. Aliquam interdum porttitor lorem, ac ultrices magna. Maecenas condimentum lorem libero, vel semper est sodales non. ', 'https://picsum.photos/200/300.jpg', 8, 66, 9, '2023-06-19 16:43:40', '2023-06-19 16:43:40', NULL),
(9, 'Post 9', 'This is the description for post 9', 'In euismod dui turpis, eu sagittis nulla vulputate ut. Cras bibendum, tellus eget varius auctor, magna nulla vestibulum nulla, non venenatis libero augue sit amet lorem. Sed in tincidunt odio. Nulla facilisi. Nullam auctor varius sapien vel accumsan. Etiam auctor metus non commodo cursus. Suspendisse non urna ac quam consectetur tempor. ', 'https://picsum.photos/200/300.jpg', 9, 69, 8, '2023-06-19 16:43:40', '2023-06-19 16:43:40', NULL),
(10, 'Post 10', 'This is the description for post 10', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed quis nibh id quam dignissim finibus. Ut lobortis malesuada tellus, ac consectetur velit viverra sed. Donec lobortis, tortor nec venenatis feugiat, neque felis congue diam, at lacinia lectus sapien eget elit. Suspendisse potenti. Etiam maximus dui nec eleifend dignissim. ', 'https://picsum.photos/200/300.jpg', 10, 72, 9, '2023-06-19 16:43:40', '2023-06-19 16:43:40', NULL),
(11, 'Post 11', 'This is the description for post 11', 'Cras bibendum, nisl vel lacinia tincidunt, massa ex dignissim lectus, eu consectetur odio turpis vel mauris. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nullam id ligula ac velit auctor facilisis. Donec id nisi in magna placerat laoreet. Sed risus orci, vestibulum eget porta non, maximus id dui. Sed congue justo non sapien tristique hendrerit. ', 'https://picsum.photos/200/300.jpg', 1, 75, 8, '2023-06-19 16:43:40', '2023-06-19 16:43:40', NULL),
(12, 'Post 12', 'This is the description for post 12', 'Curabitur posuere mollis dui, non tincidunt velit bibendum vel. Maecenas nec blandit ex. Fusce tempus nunc ac turpis consectetur, vel aliquam dolor sagittis. Etiam euismod ex eu lectus maximus, at hendrerit nibh maximus. Phasellus eget dui vitae est feugiat bibendum. Suspendisse sed sapien in quam lacinia venenatis. Ut tincidunt, tellus non laoreet volutpat, libero lorem scelerisque sapien, eget ultricies quam quam quis erat. ', 'https://picsum.photos/200/300.jpg', 2, 78, 9, '2023-06-19 16:43:40', '2023-06-19 16:43:40', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_products`
--

CREATE TABLE `tbl_products` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delete_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_products`
--

INSERT INTO `tbl_products` (`id`, `name`, `description`, `price`, `image`, `status_id`, `created_at`, `updated_at`, `delete_at`) VALUES
(1, 'Amazing Widget', 'This amazing widget will change your life!', 19.99, 'https://picsum.photos/200/300.jpg', 6, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(2, 'Dynamic Gadget', 'Get more out of life with this dynamic gadget!', 29.99, 'https://picsum.photos/200/300.jpg', 7, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(3, 'Incredible Gizmo', 'Experience the power of the incredible gizmo!', 39.99, 'https://picsum.photos/200/300.jpg', 6, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(4, 'Revolutionary Thingamajig', 'Revolutionize your life with the revolutionary thingamajig!', 49.99, 'https://picsum.photos/200/300.jpg', 7, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(5, 'Ultimate Doohickey', 'Get the ultimate doohickey for all your needs!', 59.99, 'https://picsum.photos/200/300.jpg', 6, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(6, 'Innovative Whatchamacallit', 'Discover the innovation of the whatchamacallit!', 69.99, 'https://picsum.photos/200/300.jpg', 7, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(7, 'Fantastic Whatsit', 'The fantastic whatsit will amaze and delight!', 79.99, 'https://picsum.photos/200/300.jpg', 6, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(8, 'Sensational Doodad', 'Experience the sensation of the top-of-the-line doodad!', 89.99, 'https://picsum.photos/200/300.jpg', 7, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(9, 'Premium Thingummy', 'Upgrade your life with the premium thingummy!', 99.99, 'https://picsum.photos/200/300.jpg', 6, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(10, 'State-of-the-Art Contraption', 'Get the state-of-the-art contraption for all your needs!', 109.99, 'https://picsum.photos/200/300.jpg', 7, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(11, 'Advanced Gismo', 'Take your life to the next level with the advanced gismo!', 119.99, 'https://picsum.photos/200/300.jpg', 6, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(12, 'Cutting-Edge Widget', 'Stay ahead of the curve with the cutting-edge widget!', 129.99, 'https://picsum.photos/200/300.jpg', 7, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(13, 'High-Tech Doodad', 'Experience the latest in high-tech with the top-of-the-line doodad!', 139.99, 'https://picsum.photos/200/300.jpg', 6, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(14, 'Top-of-the-Line Gizmo', 'Get the top-of-the-line gizmo for all your needs!', 149.99, 'https://picsum.photos/200/300.jpg', 7, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(15, 'Advanced Thingamajig', 'Take your life to the next level with the advanced thingamajig!', 159.99, 'https://picsum.photos/200/300.jpg', 6, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(16, 'Innovative Gadget', 'Discover the innovation of the top-of-the-line gadget!', 169.99, 'https://picsum.photos/200/300.jpg', 7, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(17, 'State-of-the-Art Widget', 'Get the state-of-the-art widget for all your needs!', 179.99, 'https://picsum.photos/200/300.jpg', 6, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(18, 'Premium Gizmo', 'Upgrade your life with the premium gizmo!', 189.99, 'https://picsum.photos/200/300.jpg', 7, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(19, 'Advanced Doohickey', 'Take your life to the next level with the advanced doohickey!', 199.99, 'https://picsum.photos/200/300.jpg', 6, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL),
(20, 'Cutting-Edge Gismo', 'Stay ahead of the curve with the cutting-edge gismo!', 209.99, 'https://picsum.photos/200/300.jpg', 7, '2023-06-19 16:24:53', '2023-06-19 16:24:53', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_ratings`
--

CREATE TABLE `tbl_ratings` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `restaurant_id` int(11) DEFAULT NULL,
  `star` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delete_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_ratings`
--

INSERT INTO `tbl_ratings` (`id`, `customer_id`, `restaurant_id`, `star`, `comment`, `status_id`, `created_at`, `updated_at`, `delete_at`) VALUES
(1, 61, 63, 4, 'The food was amazing!', 12, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(2, 64, 66, 3, 'Decent food, but nothing special.', 13, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(3, 67, 69, 5, 'Absolutely fantastic experience!', 12, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(4, 70, 72, 2, 'Disappointing food and service.', 13, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(5, 73, 75, 4, 'Great food and atmosphere!', 12, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(6, 76, 78, 5, 'Best dining experience ever!', 12, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(7, 79, 63, 3, 'Good food, but service was lacking.', 13, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(8, 61, 66, 4, 'Excellent food and service!', 12, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(9, 64, 69, 5, 'Incredible experience from start to finish!', 12, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(10, 67, 72, 2, 'Disappointing food and atmosphere.', 13, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(11, 70, 75, 4, 'Great food and service!', 12, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(12, 73, 78, 5, 'Absolutely amazing in every way!', 12, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(13, 76, 63, 4, 'Solid food and service, but nothing exceptional.', 13, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(14, 79, 66, 3, 'Decent food, but not worth the price.', 13, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL),
(15, 61, 69, 5, 'Unforgettable experience!', 12, '2023-06-19 16:38:27', '2023-06-19 16:38:27', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_reservations`
--

CREATE TABLE `tbl_reservations` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `restaurant_id` int(11) DEFAULT NULL,
  `numberOfPerson` int(11) DEFAULT NULL,
  `scheduledTime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `note` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delete_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_reservations`
--

INSERT INTO `tbl_reservations` (`id`, `customer_id`, `restaurant_id`, `numberOfPerson`, `scheduledTime`, `note`, `status_id`, `created_at`, `updated_at`, `delete_at`) VALUES
(1, 61, 63, 2, '2023-06-20 12:00:00', 'Table by the window', 1, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(2, 61, 66, 4, '2023-06-25 11:30:00', 'Anniversary dinner', 2, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(3, 61, 69, 3, '2023-07-01 13:00:00', 'Pre-show dinner', 1, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(4, 61, 72, 5, '2023-07-05 12:30:00', 'Family celebration', 2, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(5, 61, 75, 2, '2023-07-08 11:00:00', 'Quick dinner before the game', 1, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(6, 61, 78, 4, '2023-07-10 12:00:00', 'Friends gathering', 2, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(7, 64, 63, 2, '2023-06-22 12:30:00', 'Date night', 2, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(8, 64, 66, 3, '2023-06-28 13:00:00', 'Birthday dinner', 1, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(9, 64, 69, 4, '2023-07-02 11:30:00', 'Pre-concert dinner', 2, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(10, 64, 72, 5, '2023-07-06 12:00:00', 'Family reunion', 1, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(11, 64, 75, 2, '2023-07-09 11:00:00', 'Dinner before the game', 2, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(12, 64, 78, 4, '2023-07-11 13:00:00', 'Class reunion', 1, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(13, 67, 63, 3, '2023-06-23 11:00:00', 'Business dinner', 1, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(14, 67, 66, 2, '2023-06-26 12:00:00', 'Date night', 2, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(15, 67, 69, 4, '2023-07-03 13:00:00', 'Pre-fireworks dinner', 1, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(16, 67, 72, 5, '2023-07-07 12:30:00', 'Family dinner', 2, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(17, 67, 75, 3, '2023-07-08 11:30:00', 'Dinner before the game', 1, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(18, 67, 78, 2, '2023-07-12 12:00:00', 'Romantic dinner', 2, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(19, 70, 63, 4, '2023-06-24 12:00:00', 'Group dinner', 2, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL),
(20, 70, 66, 3, '2023-06-27 11:30:00', 'Family dinner', 1, '2023-06-19 16:20:02', '2023-06-19 16:20:02', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_roles`
--

CREATE TABLE `tbl_roles` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `roleKey` varchar(20) DEFAULT NULL COMMENT 'customer, admin, restaurant, user',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delete_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_roles`
--

INSERT INTO `tbl_roles` (`id`, `name`, `roleKey`, `created_at`, `updated_at`, `delete_at`) VALUES
(1, 'Customer', 'customer', '2023-06-19 16:04:15', '2023-06-19 16:04:15', NULL),
(2, 'Admin', 'admin', '2023-06-19 16:04:15', '2023-06-19 16:04:15', NULL),
(3, 'Restaurant', 'restaurant', '2023-06-19 16:04:15', '2023-06-19 16:04:15', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_status`
--

CREATE TABLE `tbl_status` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `statusKey` varchar(20) DEFAULT NULL COMMENT 'reservations: pending, active, closed; users: active, inactive; products: active, inactive; posts: active, inactive',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delete_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_status`
--

INSERT INTO `tbl_status` (`id`, `name`, `statusKey`, `created_at`, `updated_at`, `delete_at`) VALUES
(1, 'Pending', 'reservationsPending', '2023-06-19 16:08:21', '2023-06-19 16:08:21', NULL),
(2, 'Active', 'reservationsActive', '2023-06-19 16:08:21', '2023-06-19 16:08:21', NULL),
(3, 'Closed', 'reservationsClosed', '2023-06-19 16:08:21', '2023-06-19 16:08:21', NULL),
(4, 'Active', 'usersActive', '2023-06-19 16:08:21', '2023-06-19 16:08:21', NULL),
(5, 'Inactive', 'usersInactive', '2023-06-19 16:08:21', '2023-06-19 16:08:21', NULL),
(6, 'Active', 'productsActive', '2023-06-19 16:08:21', '2023-06-19 16:08:21', NULL),
(7, 'Inactive', 'productsInactive', '2023-06-19 16:08:21', '2023-06-19 16:08:21', NULL),
(8, 'Active', 'postsActive', '2023-06-19 16:08:21', '2023-06-19 16:08:21', NULL),
(9, 'Inactive', 'postsInactive', '2023-06-19 16:08:21', '2023-06-19 16:08:21', NULL),
(10, 'Active', 'categoriesActive', '2023-06-19 16:26:51', '2023-06-19 16:26:51', NULL),
(11, 'Inactive', 'categoriesInactive', '2023-06-19 16:26:51', '2023-06-19 16:26:51', NULL),
(12, 'Pending', 'ratingPending', '2023-06-19 16:37:32', '2023-06-19 16:37:32', NULL),
(13, 'Active', 'ratingActive', '2023-06-19 16:37:32', '2023-06-19 16:37:32', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `birthDate` timestamp NULL DEFAULT NULL,
  `personalID` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delete_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`id`, `name`, `email`, `role_id`, `password`, `phone`, `gender`, `birthDate`, `personalID`, `address`, `status_id`, `created_at`, `updated_at`, `delete_at`) VALUES
(61, 'John Doe', 'johndoe@gmail.com', 1, 'password123', '1234567890', 1, '1989-12-31 17:00:00', '1234567890', '123 Main St', 1, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(62, 'Jane Smith', 'janesmith@gmail.com', 2, 'password456', '0987654321', 2, '1995-05-04 17:00:00', '0987654321', '456 Elm St', 2, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(63, 'Bob Johnson', 'bobjohnson@gmail.com', 3, 'password789', '5555555554', 1, '1985-12-30 17:00:00', '5555555554', '789 Oak St', 1, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(64, 'Amy Lee', 'amylee@gmail.com', 1, 'passwordabc', '1111111111', 2, '1987-07-06 17:00:00', '1111111111', '321 Maple St', 2, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(65, 'Tom Smith', 'tomsmith@gmail.com', 2, 'passworddef', '2222222222', 1, '1993-03-02 17:00:00', '2222222222', '654 Pine St', 1, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(66, 'Emily Davis', 'emilydavis@gmail.com', 3, 'passwordegh', '3333333333', 2, '1989-09-08 17:00:00', '3333333333', '987 Cedar St', 2, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(67, 'Steve Johnson', 'stevejohnson@gmail.com', 1, 'passwordijk', '4444444444', 1, '1991-11-10 17:00:00', '4444444444', '369 Oak St', 1, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(68, 'Megan Brown', 'meganbrown@gmail.com', 2, 'passwordlmn', '5555555555', 2, '1990-02-01 17:00:00', '5555555555', '258 Pine St', 2, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(69, 'David Lee', 'davidlee@gmail.com', 3, 'passwordopq', '6666666666', 1, '1995-08-07 17:00:00', '6666666666', '753 Cedar St', 1, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(70, 'Samantha Smith', 'samanthasmith@gmail.com', 1, 'passwordrst', '7777777777', 2, '1992-04-03 17:00:00', '7777777777', '852 Maple St', 2, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(71, 'James Johnson', 'jamesjohnson@gmail.com', 2, 'passworduvw', '8888888888', 1, '1990-10-09 17:00:00', '8888888888', '456 Pine St', 1, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(72, 'Jessica Williams', 'jessicawilliams@gmail.com', 3, 'passwordxyz', '9999999999', 2, '1988-06-05 17:00:00', '9999999999', '987 Oak St', 2, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(73, 'Michael Davis', 'michaeldavis@gmail.com', 1, 'password1234', '1111111112', 1, '1992-12-31 17:00:00', '1111111112', '369 Cedar St', 1, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(74, 'Lauren Smith', 'laurensmith@gmail.com', 2, 'password5678', '2222222223', 2, '1986-05-04 17:00:00', '2222222223', '753 Pine St', 2, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(75, 'Kevin Johnson', 'kevinjohnson@gmail.com', 3, 'password9012', '3333333334', 1, '1984-12-11 17:00:00', '3333333334', '852 Cedar St', 1, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(76, 'Rachel Lee', 'rachellee@gmail.com', 1, 'password3456', '4444444445', 2, '1994-03-02 17:00:00', '4444444445', '456 Oak St', 2, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(77, 'Mark Smith', 'marksmith@gmail.com', 2, 'password7890', '5555555556', 1, '1983-09-08 17:00:00', '5555555556', '987 Pine St', 1, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(78, 'Sarah Davis', 'sarahdavis@gmail.com', 3, 'password12345', '6666666667', 2, '1991-07-06 17:00:00', '6666666667', '369 Maple St', 2, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(79, 'Chris Johnson', 'chrisjohnson@gmail.com', 1, 'password67890', '7777777778', 1, '1992-11-10 17:00:00', '7777777778', '753 Oak St', 1, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL),
(80, 'Kelly Brown', 'kellybrown@gmail.com', 2, 'passwordabcde', '8888888889', 2, '1989-04-03 17:00:00', '8888888889', '852 Cedar St', 2, '2023-06-19 16:13:15', '2023-06-19 16:13:15', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_categories`
--
ALTER TABLE `tbl_categories`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_categories_status` (`status_id`);

--
-- Indexes for table `tbl_chats`
--
ALTER TABLE `tbl_chats`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_chats_admins` (`admin_id`),
  ADD KEY `fk_chats_users` (`user_id`);

--
-- Indexes for table `tbl_postdetails`
--
ALTER TABLE `tbl_postdetails`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_postdetails_posts` (`post_id`),
  ADD KEY `fk_postdetails_products` (`product_id`);

--
-- Indexes for table `tbl_posts`
--
ALTER TABLE `tbl_posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_posts_categories` (`category_id`),
  ADD KEY `fk_posts_restaurants` (`restaurant_id`),
  ADD KEY `fk_posts_status` (`status_id`);

--
-- Indexes for table `tbl_products`
--
ALTER TABLE `tbl_products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_products_status` (`status_id`);

--
-- Indexes for table `tbl_ratings`
--
ALTER TABLE `tbl_ratings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ratings_customers` (`customer_id`) USING BTREE,
  ADD KEY `fk_ratings_restaurants` (`restaurant_id`) USING BTREE,
  ADD KEY `fk_ratings_status` (`status_id`);

--
-- Indexes for table `tbl_reservations`
--
ALTER TABLE `tbl_reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_reservations_customers` (`customer_id`),
  ADD KEY `fk_reservations_restaurant` (`restaurant_id`),
  ADD KEY `fk_reservations_status` (`status_id`);

--
-- Indexes for table `tbl_roles`
--
ALTER TABLE `tbl_roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_status`
--
ALTER TABLE `tbl_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `personalID` (`personalID`),
  ADD KEY `fk_users_status` (`status_id`),
  ADD KEY `fk_users_roles` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_categories`
--
ALTER TABLE `tbl_categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbl_chats`
--
ALTER TABLE `tbl_chats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `tbl_postdetails`
--
ALTER TABLE `tbl_postdetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `tbl_posts`
--
ALTER TABLE `tbl_posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tbl_products`
--
ALTER TABLE `tbl_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `tbl_ratings`
--
ALTER TABLE `tbl_ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `tbl_reservations`
--
ALTER TABLE `tbl_reservations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `tbl_roles`
--
ALTER TABLE `tbl_roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_status`
--
ALTER TABLE `tbl_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_categories`
--
ALTER TABLE `tbl_categories`
  ADD CONSTRAINT `fk_categories_status` FOREIGN KEY (`status_id`) REFERENCES `tbl_status` (`id`);

--
-- Constraints for table `tbl_chats`
--
ALTER TABLE `tbl_chats`
  ADD CONSTRAINT `fk_chats_admins` FOREIGN KEY (`admin_id`) REFERENCES `tbl_users` (`id`),
  ADD CONSTRAINT `fk_chats_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`);

--
-- Constraints for table `tbl_postdetails`
--
ALTER TABLE `tbl_postdetails`
  ADD CONSTRAINT `fk_postdetails_posts` FOREIGN KEY (`post_id`) REFERENCES `tbl_posts` (`id`),
  ADD CONSTRAINT `fk_postdetails_products` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`id`);

--
-- Constraints for table `tbl_posts`
--
ALTER TABLE `tbl_posts`
  ADD CONSTRAINT `fk_posts_categories` FOREIGN KEY (`category_id`) REFERENCES `tbl_categories` (`id`),
  ADD CONSTRAINT `fk_posts_restaurants` FOREIGN KEY (`restaurant_id`) REFERENCES `tbl_users` (`id`),
  ADD CONSTRAINT `fk_posts_status` FOREIGN KEY (`status_id`) REFERENCES `tbl_status` (`id`);

--
-- Constraints for table `tbl_products`
--
ALTER TABLE `tbl_products`
  ADD CONSTRAINT `fk_products_status` FOREIGN KEY (`status_id`) REFERENCES `tbl_status` (`id`);

--
-- Constraints for table `tbl_ratings`
--
ALTER TABLE `tbl_ratings`
  ADD CONSTRAINT `fk_ratings_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `tbl_users` (`id`),
  ADD CONSTRAINT `fk_ratings_status` FOREIGN KEY (`status_id`) REFERENCES `tbl_status` (`id`),
  ADD CONSTRAINT `fk_ratings_users` FOREIGN KEY (`customer_id`) REFERENCES `tbl_users` (`id`);

--
-- Constraints for table `tbl_reservations`
--
ALTER TABLE `tbl_reservations`
  ADD CONSTRAINT `fk_reservations_customers` FOREIGN KEY (`customer_id`) REFERENCES `tbl_users` (`id`),
  ADD CONSTRAINT `fk_reservations_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `tbl_users` (`id`),
  ADD CONSTRAINT `fk_reservations_status` FOREIGN KEY (`status_id`) REFERENCES `tbl_status` (`id`);

--
-- Constraints for table `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD CONSTRAINT `fk_users_roles` FOREIGN KEY (`role_id`) REFERENCES `tbl_roles` (`id`),
  ADD CONSTRAINT `fk_users_status` FOREIGN KEY (`status_id`) REFERENCES `tbl_status` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
