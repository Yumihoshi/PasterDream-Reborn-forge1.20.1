# 帕斯特之梦: 重生 / PasterDream: Reborn

![访问次数](https://starry-trace-sky-moe-counter.vercel.app/get/@PasterDream-Reborn?theme=rule34)

![编程语言](https://img.shields.io/badge/编程语言-Java_17-blue.svg?style=for-the-badge)
![构建工具](https://img.shields.io/badge/构建工具-Gradle-green.svg?style=for-the-badge)
![MC版本](https://img.shields.io/badge/MC版本-1.20.1-yellow.svg?style=for-the-badge)
![Mod加载器](https://img.shields.io/badge/Mod加载器-Forge_47.4.10-orange.svg?style=for-the-badge)

重写原版帕斯特之梦模组，基于 Minecraft Forge 1.20.1，从空 MDK 从零搭建。

A community rewrite of the original PasterDream mod, rebuilt from scratch on Minecraft Forge 1.20.1.

## 授权 / Authorization

本项目已获得原作者"异星之尘"的授权发布，沿用原模组 ID `pasterdream`。美术资源（纹理、模型、音效等）大部分来自原模组，采用 ARR 协议，版权归原作者及原模组美术贡献者所有；部分贴图已由重写团队重新绘制或全新创作，版权归重写团队所有。详见 [ASSETS_MANIFEST.md](src/main/resources/ASSETS_MANIFEST.md)。

This project is released with permission from the original author "Aerolite_Dust." The mod ID `pasterdream` is retained. Most art assets (textures, models, sounds, etc.) are from the original mod, licensed under All Rights Reserved, copyright by the original author and contributors. Some textures have been redrawn or newly created by the rewrite team, copyright by the rewrite team. See [ASSETS_MANIFEST.md](src/main/resources/ASSETS_MANIFEST.md).

## 协议 / License

- **代码 / Code**: [MIT](LICENSE.md) — 可自由使用、修改、分发
- **美术资源 / Art Assets**: [ARR](src/main/resources/LICENSE_ASSETS.md) — 保留所有权利。原模组资产版权归原作者，重写团队资产版权归重写团队。详见 [资产清单](src/main/resources/ASSETS_MANIFEST.md)

## 安装信息

克隆仓库后，在 IDE 中打开即可。推荐使用 IntelliJ IDEA。

如果 IDE 中缺少库或遇到问题，可以运行以下命令：

```bash
./gradlew --refresh-dependencies  # 刷新本地缓存
./gradlew clean                   # 重置构建（不影响代码）
```

> **注意**：如果遇到中文注释乱码，请将 IDE 项目编码设置为 **UTF-8**（File → Settings → Editor → File Encodings → Project Encoding 和 Default encoding for properties files 均设为 UTF-8）。

## 构建

```bash
./gradlew build       # 构建 Jar 包
./gradlew runClient   # 启动客户端
./gradlew runData     # 生成数据文件
```

## 依赖 / Dependencies

| 前置 | 版本要求 |
|------|----------|
| Minecraft Forge | 47+ |
| Curios | 5.14.1+ |
| GeckoLib | 4.8.3+ |
| JEI (可选) | 15.20.0.112+ |

## 文档 / Documentation

项目文档见 [`document/`](document/) 目录：

- [`document/design/`](document/design/) — 策划文档、ID 映射表、命名规范
- [`document/rule/`](document/rule/) — 代码架构规范
- [`document/reference/`](document/reference/) — 源模组参考文件

## 致谢 / Credits

- **原作 / Original**: 异星之尘 (Aerolite_Dust) — 感谢授权和支持 / Thanks for authorization and support
- **策划 / Design**: 拭柳喑雨 (ShiLiuYinYu)
- **程序 / Programming**: 2884omgpy, ShiLiuYinYu, Garam
- **美术 / Art**: 煮饭婆 (GQ2529), Garam

## 其他资源

Forge 社区文档：https://docs.minecraftforge.net/  
Forge Discord：https://discord.minecraftforge.net/
